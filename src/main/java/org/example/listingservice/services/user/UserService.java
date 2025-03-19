package org.example.listingservice.services.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.listingservice.constant.MessageKeys;
import org.example.listingservice.converter.Converter;
import org.example.listingservice.dtos.PasswordDTO;
import org.example.listingservice.dtos.RegisterDTO;
import org.example.listingservice.dtos.UserDTO;
import org.example.listingservice.exceptions.DataNotFoundException;
import org.example.listingservice.models.Conversation;
import org.example.listingservice.models.Role;
import org.example.listingservice.models.Token;
import org.example.listingservice.models.User;
import org.example.listingservice.repositories.ConversationRepository;
import org.example.listingservice.repositories.RoleRepository;
import org.example.listingservice.repositories.UserRepository;
import org.example.listingservice.responses.user.LoginResponse;
import org.example.listingservice.responses.user.UserListResponse;
import org.example.listingservice.responses.user.UserResponse;
import org.example.listingservice.services.DriveService;
import org.example.listingservice.services.caches.CacheService;
import org.example.listingservice.services.rabbitMQ.RabbitMQProducer;
import org.example.listingservice.services.token.TokenService;
import org.example.listingservice.utils.JwtUtils;
import org.example.listingservice.utils.LocalizationUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final Converter converter;
    private final DriveService driveService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final LocalizationUtils localizationUtils;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final RabbitMQProducer rabbitMQProducer;
    private final ConversationRepository conversationRepository;
    private final CacheService cacheService;

    @Override
    @Cacheable(value={"caffeineUsers","redisUsers"}, key="#keyword")
    public UserListResponse getAllByKeyword(String keyword, int page, int limit) {
        int totalPages = 0;
        PageRequest pageRequest = PageRequest.of(page,limit);
        Page<User> pages = userRepository.findAllByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneEqualsIgnoreCase(keyword,keyword,keyword,pageRequest);
        List<UserResponse> userResponses = pages.getContent()
                .stream().map(UserResponse::fromUser).toList();
        totalPages = pages.getTotalPages();
        return UserListResponse.builder()
                .totalPages(totalPages)
                .userResponses(userResponses)
                .build();
    }

    @Override
    @Cacheable(value={"caffeineUser","redisUser"}, key="#id")
    public UserResponse getById(Long id) throws DataNotFoundException {
        return UserResponse.fromUser(userRepository
                .findById(id).orElseThrow(()->
                        new DataNotFoundException(MessageKeys.DATA_NOT_FOUND)));
    }

    @Override
    @CacheEvict(value={"caffeineUser","redisUser","caffeineUsers","redisUsers"},allEntries = true)
    public String deleteById(Long id) {
        if(userRepository.existsById(id)){

            userRepository.deleteById(id);
            return MessageKeys.DELETE_SUCCESSFULLY;
        }
        return MessageKeys.DATA_NOT_FOUND;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = {"caffeineUsers","redisUsers"},allEntries = true, condition = "#dto.id != null ")
            },
            put = {
                    @CachePut(value = {"caffeineUser", "redisUser"}, key="#dto.id", condition = "#dto.id != null ")
            }
    )
    public ResponseEntity<?> createOrUpdate(UserDTO dto) throws DataNotFoundException {
        User existingUser = userRepository
                .findById(dto.getId()).orElseThrow(()->
                        new DataNotFoundException(MessageKeys.DATA_NOT_FOUND));
        existingUser.setEmail(dto.getEmail());
        if(userRepository.existsByPhone(dto.getPhone())){
            if(!dto.getPhone().equals(existingUser.getPhone())) {
                return  ResponseEntity.badRequest().body(MessageKeys.PHONE_NUMBER_EXISTED);
            }
        }
        existingUser.setPhone(dto.getPhone());
        existingUser.setFullName(dto.getFullName());
        cacheService.deleteUserFromCache(dto.getId());
        return ResponseEntity.ok().body(UserResponse.fromUser(userRepository.saveAndFlush(existingUser)));
    }

    @Override
    public UserResponse updateAvatar(Long id, MultipartFile file) throws DataNotFoundException, GeneralSecurityException, IOException {
        User existingUser = userRepository
                .findById(id).orElseThrow(()->
                        new DataNotFoundException(MessageKeys.DATA_NOT_FOUND));
        String fileId = driveService.extractFileIdFromUrl(existingUser.getAvatar());
        if(fileId!=null){
            driveService.deleteImageFromDrive(fileId);
        }
        File avatarTempFile = File.createTempFile("avatar", null);
        file.transferTo(avatarTempFile);
        String avatarUrl = driveService.uploadImageToDrive(avatarTempFile);
        existingUser.setAvatar(avatarUrl);
       return UserResponse.fromUser(userRepository.saveAndFlush(existingUser));
    }

    @Override
    public ResponseEntity<?> register(RegisterDTO dto) throws DataNotFoundException {
        if(userRepository.existsByPhone(dto.getPhone()))
        {
            return ResponseEntity.badRequest().body(MessageKeys.PHONE_NUMBER_EXISTED);
        }
        User newUser = converter.fromRegisterDTO(dto);
        newUser.setId(null);

        Role existingRole  = roleRepository.findById(dto.getRoleId())
                .orElseThrow(()->new DataNotFoundException(MessageKeys.DATA_NOT_FOUND));

        newUser.setPassword(encodePassword(newUser.getPassword()));
        newUser.setRole(existingRole);
        newUser.setUsedPost(0L);
        newUser.setRemainPost(2L);
        newUser.setTotalPaid(0.0);

        rabbitMQProducer.sendMailToQueue(dto);

        return ResponseEntity.ok().body(UserResponse.fromUser(userRepository.saveAndFlush(newUser)));
    }

    @Override
    public ResponseEntity<?> resetPassword(PasswordDTO dto, Long userId) throws DataNotFoundException {
        User existingUser = userRepository
                .findById(userId).orElseThrow(()->
                        new DataNotFoundException(MessageKeys.DATA_NOT_FOUND));
        if(!checkPassword(dto.getOldPassword(),existingUser.getPassword())){
            return ResponseEntity.badRequest().body(MessageKeys.PASSWORD_NOT_MATCH);
        }
        if(checkPassword(dto.getNewPassword(),existingUser.getPassword())){
            return ResponseEntity.badRequest().body(MessageKeys.NEW_PASSWORD_EQUAL_OLD_PASSWORD);
        }
        if(!dto.getNewPassword().equals(dto.getReNewPassword())){
            return ResponseEntity.badRequest().body(MessageKeys.PASSWORD_NOT_MATCH);
        }
        existingUser.setPassword(encodePassword(dto.getNewPassword()));
        userRepository.saveAndFlush(existingUser);
        return ResponseEntity.ok().body(MessageKeys.UPDATE_SUCCESSFULLY);
    }

    @Override
    public LoginResponse login(String phone, String password, HttpServletRequest request) throws Exception {
        LoginResponse loginResponse = new LoginResponse();
        User user = userRepository.findByPhone(phone)
                .orElseThrow(()-> new DataNotFoundException(MessageKeys.DATA_NOT_FOUND));
        if(!passwordEncoder.matches(password, user.getPassword())){
            loginResponse.setMessage(MessageKeys.WRONG_PHONE_PASSWORD);
        }
        else{
            if(user.getStatus().equals(0)) {
                throw new DataNotFoundException(localizationUtils.getLocalizedMesage(MessageKeys.USER_IS_LOCKED));
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    phone,password,user.getAuthorities()
            );
            authenticationManager.authenticate(authenticationToken);

            loginResponse.setId(user.getId());
            loginResponse.setToken(jwtUtils.generateToken(user));
            loginResponse.setFullname(user.getFullName());
            loginResponse.setMessage(MessageKeys.LOGIN_SUCCESSFULLY);
            loginResponse.setRefreshToken(loginResponse.getToken());
            loginResponse.setRoles(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
            loginResponse.setAvatarLink(user.getAvatar());
            loginResponse.setRemainPosts(user.getRemainPost());

            //thêm mới token vào database
            String userAgent = request.getHeader("User-Agent");
            Token savedToken = tokenService.addToken(user,loginResponse.getToken(),isMobileDevice(userAgent));
            loginResponse.setRefreshToken(savedToken.getRefreshToken());
        }

        return loginResponse;
    }

    @Override
    public UserListResponse getAllContactedByUserId(Long userId) {
       List<Conversation> conversations = conversationRepository.findAllBySender_IdOrReceiver_Id(userId,userId);
       List<Long> contactIdList = conversations.stream().map((c) -> {
           return c.getSender().getId().equals(userId) ? c.getReceiver().getId() : c.getSender().getId();
       }).toList();
        List<User> contacteds = userRepository.findAllByIdIsIn(contactIdList);
        List<UserResponse> responses = contacteds.stream().map(UserResponse::fromUser).toList();
        return UserListResponse.builder()
                .userResponses(responses)
                .build();
    }

    public static String encodePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
    public static boolean checkPassword(String enteredPassword, String encodedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, encodedPassword);
    }
    private boolean isMobileDevice(String userAgent) {
        return userAgent.toLowerCase().contains("mobile");
    }

    public boolean reset(){
        try{
            List<User> existingUsers = userRepository.findAll();
            existingUsers = existingUsers.stream()
                    .peek(user-> user.setUsedPost(0L))
                    .peek(user->user.setRemainPost(2L))
                    .collect(Collectors.toList());
            userRepository.saveAll(existingUsers);
        }catch(Exception e){
            return false;
        }
        return true;
    }


}
