package org.example.listingservice.services.user;

import jakarta.servlet.http.HttpServletRequest;
import org.example.listingservice.dtos.PasswordDTO;
import org.example.listingservice.dtos.RegisterDTO;
import org.example.listingservice.dtos.UserDTO;
import org.example.listingservice.responses.user.LoginResponse;
import org.example.listingservice.responses.user.UserListResponse;
import org.example.listingservice.responses.user.UserResponse;
import org.example.listingservice.exceptions.DataNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface IUserService {
    UserListResponse getAllByKeyword(String keyword, int page, int limit);
    UserResponse getById(Long id) throws DataNotFoundException;
    String deleteById(Long id);
    ResponseEntity<?> createOrUpdate(UserDTO dto) throws DataNotFoundException;
    UserResponse updateAvatar(Long id, MultipartFile file) throws DataNotFoundException, GeneralSecurityException, IOException;
    ResponseEntity<?> register(RegisterDTO dto) throws DataNotFoundException;
    ResponseEntity<?> resetPassword(PasswordDTO dto, Long userId) throws DataNotFoundException;
    LoginResponse login(String phone, String password, HttpServletRequest request) throws Exception;
    UserListResponse getAllContactedByUserId(Long userId);
}
