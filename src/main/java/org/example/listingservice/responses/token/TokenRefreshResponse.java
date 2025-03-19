package org.example.listingservice.responses.token;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenRefreshResponse {
    @JsonProperty("new_access_token")
   private String newAccessToken;

    @JsonProperty("expiration_date")
    private LocalDateTime expirationDate;
}
