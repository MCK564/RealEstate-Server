package org.example.listingservice.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String phone;
    private String password;
    private String fullname;
    private String email;
    private int status;
    @JsonProperty("role_id")
    private Long roleId;
}
