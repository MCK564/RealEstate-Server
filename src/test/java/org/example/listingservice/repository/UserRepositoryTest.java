package org.example.listingservice.repository;


import org.example.listingservice.models.Role;
import org.example.listingservice.models.User;
import org.example.listingservice.repositories.UserRepository;
import org.example.listingservice.services.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("integration tests for UserRepository ")
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private User user1;
    private User user2;

    @Mock
    private Role mockRole = new Role("Người dùng","USER");

    @BeforeEach
    void setUp(){
        mockRole.setId(2L);
        user1 = User.builder()
                .status(1)
                .phone("565656")
                .email("user1_testing@gmai.com")
                .password(UserService.encodePassword("123456"))
                .fullName("User1")
                .usedPost(0L)
                .role(mockRole)
                .remainPost(2L)
                .build();

        user2 = User.builder()
                .status(1)
                .phone("675757")
                .email("user2_testing@gmai.com")
                .password(UserService.encodePassword("123456"))
                .fullName("User2")
                .usedPost(0L)
                .role(mockRole)
                .remainPost(2L)
                .build();
    }

    @Test
    void testExistByPhone(){
        boolean exists = userRepository.existsByPhone("123456");
        assertThat(exists).isTrue();

        exists = userRepository.existsByPhone("000000");
        assertThat(exists).isFalse();
    }

   @Test
    void testFindByPhone(){
       Optional<User> user = userRepository.findByPhone("123456");
       assertThat(user).isPresent();
       assertThat(user.get().getFullName()).isEqualTo("admin");

       Optional<User> noUser= userRepository.findByPhone("1111111111");
       assertThat(noUser).isNotPresent();
   }


   @Test
    void testFindAllWithKeyword(){
       Pageable pageable = PageRequest.of(0,10);

       Page<User> result = userRepository.findAll("mck", pageable);
       assertThat(result.getContent()).hasSize(1);
       assertThat(result.getContent().get(0).getEmail()).isEqualTo("mck@gmail.com");

       result = userRepository.findAll("user", pageable);
       assertThat(result.getContent()).hasSize(4);

   }







}
