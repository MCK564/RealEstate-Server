package org.example.listingservice.services.token;


import jakarta.transaction.Transactional;
import org.example.listingservice.models.Token;
import org.example.listingservice.repositories.TokenRepository;
import org.example.listingservice.responses.token.TokenRefreshResponse;
import org.example.listingservice.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.example.listingservice.exceptions.DataNotFoundException;
import org.example.listingservice.exceptions.ExpiredTokenException;
import org.example.listingservice.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService implements  ITokenService{
    private static final int MAX_TOKENS = 3;
    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.expiration-refresh-token}")
    private int expirationRefreshToken;

    private final TokenRepository tokenRepository;
    private final JwtUtils jwtTokenUtil;

    @Transactional
    @Override
    public TokenRefreshResponse refreshToken(String refreshToken) throws Exception{
        Token existingToken = tokenRepository.findByRefreshToken(refreshToken);
        if(existingToken == null) {
            throw new DataNotFoundException("Refresh token does not exist");
        }
        if(existingToken.getRefreshExpirationDate().isBefore(LocalDateTime.now())){
            tokenRepository.delete(existingToken);
            throw new ExpiredTokenException("Refresh token is expired");
        }
        String newAccessToken = jwtTokenUtil.generateToken(existingToken.getUser());
        LocalDateTime expirationDateTime = LocalDateTime.now().plusSeconds(expiration);
        existingToken.setExpirationDate(expirationDateTime);
        existingToken.setToken(newAccessToken);
        existingToken.setRefreshExpirationDate(LocalDateTime.now().plusSeconds(expirationRefreshToken));

        Token savedToken =  tokenRepository.saveAndFlush(existingToken);
        return TokenRefreshResponse.builder()
                .newAccessToken(savedToken.getToken())
                .expirationDate(savedToken.getExpirationDate())
                .build();
    }

    @Override
    @Transactional
    public void resetSystem() throws Exception{
//        tokenRepository.deleteAll();
    }

    @Transactional
    @Override
    public Token addToken(User user,String token, boolean isMobileDevice) {
        List<Token> userTokens = tokenRepository.findByUser(user);
        int tokenCount = userTokens.size();
        if (tokenCount >= MAX_TOKENS) {
            boolean hasNonMobileToken = !userTokens.stream().allMatch(Token::isMobile);
            Token tokenToDelete;
            if (hasNonMobileToken) {
                tokenToDelete = userTokens.stream()
                        .filter(userToken -> !userToken.isMobile())
                        .findFirst()
                        .orElse(userTokens.get(0));
            } else {
                tokenToDelete = userTokens.get(0);
            }
            tokenRepository.delete(tokenToDelete);
        }
        long expirationInSeconds = expiration;
        LocalDateTime expirationDateTime = LocalDateTime.now().plusSeconds(expirationInSeconds);
        Token newToken = Token.builder()
                .user(user)
                .token(token)
                .revoked(false)
                .expired(false)
                .tokenType("Bearer")
                .expirationDate(expirationDateTime)
                .isMobile(isMobileDevice)
                .build();

        newToken.setRefreshToken(UUID.randomUUID().toString());
        newToken.setRefreshExpirationDate(LocalDateTime.now().plusSeconds(expirationRefreshToken));
        if(
                !tokenRepository.existsByToken(newToken.getToken())
        )
                tokenRepository.save(newToken);
        return newToken;
    }


}
