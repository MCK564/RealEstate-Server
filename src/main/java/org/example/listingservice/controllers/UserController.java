package org.example.listingservice.controllers;

import jakarta.servlet.http.HttpServletRequest;

import org.example.listingservice.services.user.UserService;
import org.example.listingservice.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.example.listingservice.constant.MessageKeys;
import org.example.listingservice.dtos.*;
import org.example.listingservice.services.rabbitMQ.RabbitMQProducer;
import org.example.listingservice.services.token.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/users")
@Validated
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final LocalizationUtils localizationUtils;
    private final TokenService tokenService;
    private final RabbitMQProducer rabbitMQProducer;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody() LoginDTO dto,
            HttpServletRequest request,
            BindingResult result
            ){
        try{
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(userService.login(dto.getPhoneNumber(),dto.getPassword(),request))
                    ;
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(MessageKeys.LOGIN_FAILED+ " " + e.getMessage());
        }
    }


    @GetMapping("/search")
    public ResponseEntity<?> getAllByKeyword(
            @RequestParam String keyword,
            @RequestParam  int page,
            @RequestParam int limit){
        try{
            return ResponseEntity.ok().body(userService.getAllByKeyword(keyword,page,limit));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterDTO registerDTO,
            BindingResult result){
        try{
            if(result.hasErrors()){
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            if(registerDTO.getRoleId() == 1 ){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Không thể đăng ký tài khoản hoặc admin");
            }
            return ResponseEntity.ok().body(userService.register(registerDTO));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById( @PathVariable("id")Long id){
        try{
           return ResponseEntity.ok().body(userService.getById(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id")Long id){
        try{
            return ResponseEntity.ok().body(userService.deleteById(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping(value="/avatar/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAvatar(@PathVariable("id")Long id,
                                          @RequestParam("avatar")MultipartFile file
                                          ){
        try{
            if(file.getSize()>10*1024*1024){ //10MB
                return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(
                        localizationUtils.getLocalizedMesage(MessageKeys.UPLOAD_IMAGES_FILE_LARGE));
            }
            String contentType = file.getContentType();
            if(contentType == null || !contentType.startsWith("image/")){
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(
                        localizationUtils.getLocalizedMesage(MessageKeys.UPLOAD_IMAGES_FILE_MUST_BE_IMAGE)
                );
            }
            return ResponseEntity.ok().body(userService.updateAvatar(id,file));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDto){
        try{
            return userService.createOrUpdate(userDto);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordDTO dto,
                                           @PathVariable("id") Long id){
        try{
            return userService.resetPassword(dto,id);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenDTO dto){
        String requestRefreshToken = dto.getRefreshToken();
        try{
            return ResponseEntity.ok().body("");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/reset_post")
    public ResponseEntity<?> resetPost(){
        return ResponseEntity.ok(userService.reset());
    }

    @GetMapping("/contacteds/{id}")
    public ResponseEntity<?> getListContactedUser(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getAllContactedByUserId(id));
    }

}
