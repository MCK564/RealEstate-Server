package org.example.listingservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.example.listingservice.dtos.LoginDTO;
import org.example.listingservice.filters.JwtFilter;
import org.example.listingservice.responses.user.LoginResponse;
import org.example.listingservice.services.user.UserService;
import org.example.listingservice.utils.JwtUtils;
import org.example.listingservice.utils.LocalizationUtils;
import org.example.listingservice.services.token.TokenService;
import org.example.listingservice.services.rabbitMQ.RabbitMQProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("Integration tests for User API endpoint")
@Tag("integration")
public class UserControllerTest {
    @Value("${api.prefix}")
    private String apiPrefix;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LocalizationUtils localizationUtils;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private RabbitMQProducer rabbitMQProducer;

    private String token;


    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private JwtFilter filter;


    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void testLogin_Success() throws Exception {
        LoginDTO dto = new LoginDTO();
        dto.setPhoneNumber("123456");
        dto.setPassword("123456");


        String json = this.objectMapper.writeValueAsString(dto);
        System.out.println(json);


        LoginResponse loginResponse = LoginResponse.builder()
                .id(1L)
                .fullname("admin")
                .token("mock-token-value")
                .build();


        when(userService.login(dto.getPhoneNumber(), dto.getPassword(), mock(HttpServletRequest.class)))
                .thenReturn(loginResponse);


        mockMvc.perform(post("/api/v1/users/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mock-token-value")) // Kiểm tra token có tồn tại và đúng giá trị
                .andExpect(jsonPath("$.fullname").value("admin")); // Kiểm tra fullname có giá trị "admin"
    }

    @Test
    public void testLogin_Failure_InvalidCredentials() throws Exception {

        LoginDTO dto = new LoginDTO();
        dto.setPhoneNumber("wrongPhone");
        dto.setPassword("wrongPassword");

        String json = this.objectMapper.writeValueAsString(dto);


        when(userService.login(dto.getPhoneNumber(), dto.getPassword(), mock(HttpServletRequest.class)))
                .thenThrow(new RuntimeException("Invalid credentials"));


        mockMvc.perform(post("/api/v1/users/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$").value("LOGIN_FAILED Invalid credentials")); // Kiểm tra thông báo lỗi
    }
}
