package org.example.listingservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class UserDTO {
    private Long id;
    @JsonProperty("fullname")
    private String fullName;

    @NotBlank(message = "phone must not be null")
    private String phone;

    @NotBlank(message = "email must not be null")
    private String email;
}
