package org.example.listingservice.responses.building;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.listingservice.enums.districtCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.listingservice.enums.typeCode;
import org.example.listingservice.models.Building;
import org.example.listingservice.models.BuildingImage;
import org.example.listingservice.models.RentArea;
import org.example.listingservice.responses.user.UserResponse;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuildingEditResponse {
    private Long id;
    @JsonProperty("rent_areas")
    private List<Long> rentAreas;
    @JsonProperty("images")
    private List<String> images;
    @JsonProperty("likes")
    private List<UserResponse> likes;
    @JsonProperty("name")
    private String name;
    @JsonProperty("street")
    private String street;
    @JsonProperty("ward")
    private String ward;
    @JsonProperty("district")
    private String district;
    @JsonProperty("structure")
    private String structure;
    @JsonProperty("number_of_basement")
    private Integer numberOfBasement;
    @JsonProperty("floor_area")
    private Integer floorArea;
    @JsonProperty("direction")
    private String direction;
    @JsonProperty("level")
    private String level;
    @JsonProperty("rent_price")
    private Integer rentPrice;
    @JsonProperty("rent_price_description")
    private String rentPriceDescription;
    @JsonProperty("service_fee")
    private String serviceFee;
    @JsonProperty("car_fee")
    private String carFee;
    @JsonProperty("motorbike_fee")
    private String motorbikeFee;
    @JsonProperty("overtime_fee")
    private String overtimeFee;
    @JsonProperty("water_fee")
    private String waterFee;
    @JsonProperty("electricity_fee")
    private String electricityFee;
    @JsonProperty("deposit")
    private String deposit;
    @JsonProperty("payment")
    private String payment;
    @JsonProperty("rent_time")
    private String rentTime;
    @JsonProperty("decoration_time")
    private String decorationTime;
    @JsonProperty("brokerage_fee")
    private BigDecimal brokerageFee;
    @JsonProperty("note")
    private String note;
    @JsonProperty("link_of_building")
    private String linkOfBuilding;
    @JsonProperty("map")
    private String map;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("manager_name")
    private String managerName;
    @JsonProperty("manager_phone_number")
    private String managerPhoneNumber;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("type")
    private String type;
    @JsonProperty("owner_id")
    private Long ownerId;
    @JsonProperty("address")
    private String address;
    @JsonProperty("type_string")
    private List<String> typeString;

    @JsonProperty("owner")
    private UserResponse owner;

    public static BuildingEditResponse fromBuilding(Building b) {
        String[] types = b.getType().split(",");

        return BuildingEditResponse.builder()
                .id(b.getId())
                .rentAreas(b.getRentAreas().stream().map(RentArea::getValue).collect(Collectors.toList()))
                .images(b.getImages().stream().map(BuildingImage::getImageUrl).collect(Collectors.toList()))
                .likes(b.getLikes().stream().map(love -> UserResponse.fromUser(love.getUser())).collect(Collectors.toList()))
                .name(b.getName())
                .street(b.getStreet())
                .ward(b.getWard())
                .district(b.getDistrict())
                .structure(b.getStructure())
                .numberOfBasement(b.getNumberOfBasement())
                .floorArea(b.getFloorArea())
                .direction(b.getDirection())
                .level(b.getLevel())
                .rentPrice(b.getRentPrice())
                .rentPriceDescription(b.getRentPriceDescription())
                .serviceFee(b.getServiceFee())
                .carFee(b.getCarFee())
                .motorbikeFee(b.getMotorbikeFee())
                .overtimeFee(b.getOvertimeFee())
                .waterFee(b.getWaterFee())
                .electricityFee(b.getElectricityFee())
                .deposit(b.getDeposit())
                .payment(b.getPayment())
                .rentTime(b.getRentTime())
                .decorationTime(b.getDecorationTime())
                .brokerageFee(b.getBrokerageFee())
                .note(b.getNote())
                .linkOfBuilding(b.getLinkOfBuilding())
                .map(b.getMap())
                .avatar(b.getAvatar())
                .managerName(b.getManagerName())
                .managerPhoneNumber(b.getManagerPhoneNumber())
                .status(b.getStatus())
                .type(b.getType())
                .ownerId(b.getUser().getId())
                .address(b.getStreet() + ", " + b.getWard() + ", " + districtCode.district().get(b.getDistrict()))
                .typeString(Arrays.stream(types).map((e)-> typeCode.type().get(e)).toList())
                .owner(UserResponse.fromUser(b.getUser()))
                .build();
    }
}
