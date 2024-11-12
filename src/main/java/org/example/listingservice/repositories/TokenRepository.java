package org.example.listingservice.repositories;



import org.example.listingservice.models.Token;
import org.example.listingservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findByUser(User user);
    Token findByToken(String token);
    Token findByRefreshToken(String token);
    boolean existsByToken(String token);

    void deleteByUser(User user);
}

