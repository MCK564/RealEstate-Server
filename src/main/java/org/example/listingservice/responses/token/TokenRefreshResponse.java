package org.example.listingservice.responses.token;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenRefreshResponse {
    private String jwt;

    @JsonProperty("refresh_token")
    private String refreshToken;
}
