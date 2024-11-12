package org.example.listingservice.responses.building;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.listingservice.enums.districtCode;
import lombok.*;
import org.example.listingservice.models.Building;
import org.example.listingservice.models.BuildingImage;
import org.example.listingservice.models.Love;
import org.example.listingservice.models.User;
import org.example.listingservice.responses.user.UserResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuildingResponse {
    private Long id;
    private String name;
    private String address;
    private Integer numberOfBasement;
    private Integer floorArea;
    private String direction;
    private String level;
    private String managerName;
    private String managerPhoneNumber;
    private String rentArea;
    private String rentPriceDesc;
    private Integer rentPrice;
    private String avatarUrl;
    private List<String> sliderImagesUrl = new ArrayList<>();
    private String message;
    private int status;
    private UserResponse owner ;
    private String type;
    @JsonProperty("liker_ids")
    private List<Long> likerIDs = new ArrayList<>();
    @JsonProperty("modified_date")
    private LocalDateTime modifiedDate;

    public static BuildingResponse fromBuilding(Building b){
        List<User> likers = b.getLikes().stream().map(Love::getUser).toList();
        List<Long> likerIds =  likers.stream().map(User::getId).toList();
        return BuildingResponse.builder()
                .id(b.getId())
                .name(b.getName())
                .address(b.getStreet()+", "+ b.getWard()+", "+ districtCode.district().get(b.getDistrict()))
                .rentArea(String.valueOf(b.getRentAreas().stream().map(e->e.getValue().toString()).collect(Collectors.toList())))
                .numberOfBasement(b.getNumberOfBasement())
                .floorArea(b.getFloorArea())
                .direction(b.getDirection())
                .managerName(b.getManagerName())
                .managerPhoneNumber(b.getManagerPhoneNumber())
                .level(b.getLevel())
                .rentPrice(b.getRentPrice())
                .rentPriceDesc(b.getRentPriceDescription())
                .avatarUrl(b.getAvatar())
                .status(b.getStatus())
                .sliderImagesUrl(b.getImages()
                        .stream().map(BuildingImage::getImageUrl)
                        .collect(Collectors.toList()))
                .owner(UserResponse.fromUser(b.getUser()))
                .type(b.getType())
                .likerIDs(likerIds)
                .modifiedDate(b.getModifiedDate())
                .build();
    }
}