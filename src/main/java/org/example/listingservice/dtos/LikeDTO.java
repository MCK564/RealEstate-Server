package org.example.listingservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("building_id")
    private Long buildingId;

    private Boolean like;
}
