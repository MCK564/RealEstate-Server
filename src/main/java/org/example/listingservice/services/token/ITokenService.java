package org.example.listingservice.services.token;




import org.example.listingservice.models.Token;
import org.example.listingservice.models.User;
import org.example.listingservice.responses.token.TokenRefreshResponse;
import org.springframework.stereotype.Service;

@Service
public interface ITokenService {
    Token addToken(User user, String token, boolean isMobileDevice);
    TokenRefreshResponse refreshToken(String refreshToken) throws Exception;
    void resetSystem() throws Exception;
}
