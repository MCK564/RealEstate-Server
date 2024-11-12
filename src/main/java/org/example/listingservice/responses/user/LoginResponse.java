package org.example.listingservice.responses.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private Long id;
    private String message;
    private String token;
    @JsonProperty("refresh_token")
    private String refreshToken;
    private String tokenType = "Bearer";
    private String fullname;

    @JsonProperty("avatar_link")
    private String avatarLink;
    private List<String> roles;

    @JsonProperty("remain_posts")
    private Long remainPosts;
}
