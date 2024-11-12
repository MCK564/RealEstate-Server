package org.example.listingservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CommunicationDTO {
    private String phone;
    private String note;
    @JsonProperty("buyer_id")
    private Long buyerId;

    @JsonProperty("building_id")
    private Long buildingId;
}
