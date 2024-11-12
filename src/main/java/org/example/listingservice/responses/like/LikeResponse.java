package org.example.listingservice.responses.like;

import org.example.listingservice.models.Love;
import org.example.listingservice.responses.building.BuildingResponse;
import org.example.listingservice.responses.user.UserResponse;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeResponse {


   private Long id;
   private UserResponse userResponse;
   private BuildingResponse buildingResponse;

   public static LikeResponse fromBuildingLike(Love like){
      return LikeResponse.builder().id(like.getId())
              .userResponse(UserResponse.fromUser(like.getUser()))
              .build();
   }
   public static LikeResponse fromUserLike(Love like){
      return LikeResponse.builder().id(like.getId())
              .buildingResponse(BuildingResponse.fromBuilding(like.getBuilding()))
              .build();
   }


}
