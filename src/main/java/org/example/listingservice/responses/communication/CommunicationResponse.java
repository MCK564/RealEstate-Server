package org.example.listingservice.responses.communication;

import org.example.listingservice.models.Communication;
import org.example.listingservice.responses.building.BuildingResponse;
import org.example.listingservice.responses.user.UserResponse;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunicationResponse {
    private Long id;
    private String phone;
    private String note;
    private BuildingResponse buildingResponse;
    private UserResponse buyer;
    private UserResponse saler;
    private String message;


    public static CommunicationResponse fromCommunication(Communication cm){
        return CommunicationResponse.builder()
                .id(cm.getId())
                .phone(cm.getPhone())
                .note(cm.getNote())
                .buildingResponse(BuildingResponse.fromBuilding(cm.getBuilding()))
                .buyer(UserResponse.fromUser(cm.getBuyerRenter()))
                .saler(UserResponse.fromUser(cm.getSaler()))
                .build();
    }

}
