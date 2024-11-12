package org.example.listingservice.service;

import org.example.listingservice.exceptions.DataNotFoundException;
import org.example.listingservice.models.User;
import org.example.listingservice.repositories.RoleRepository;
import org.example.listingservice.repositories.UserRepository;
import org.example.listingservice.responses.user.UserListResponse;
import org.example.listingservice.services.user.UserService;
import org.example.listingservice.utils.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DisplayName("Integration tests for UserService ")
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private JwtUtils jwtUtils;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetAllByKeyword() {
        String keyword = "1234567";
        PageRequest request = PageRequest.of(0, 10);

        User user = User.builder()
                .fullName("cus2")
                .phone("1234567")
                .email("abc123@gmail.com")
                .status(1)
                .build();
        user.setId(16L);


        List<User> users = new ArrayList<>();
        users.add(user);


        Page<User> pages = new PageImpl<>(users, request, users.size());


//        when(userRepository.findAll(keyword, request)).thenReturn(pages);


        UserListResponse listResponse = userService.getAllByKeyword(keyword, 0, 10);

        assertNotNull(listResponse);
        assertEquals(1, listResponse.getUserResponses().size());
        assertEquals("cus2", listResponse.getUserResponses().get(0).getFullName());


        verify(userRepository, times(1))
                .findAllByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneEqualsIgnoreCase(keyword,keyword,keyword,request);
    }

}
