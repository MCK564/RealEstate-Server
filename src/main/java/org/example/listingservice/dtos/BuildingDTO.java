package org.example.listingservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDTO {
    private Long id;
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
    private Integer numberOfBasement; // Thay đổi thành "number_of_basement"
    @JsonProperty("floor_area")
    private Integer floorArea; // Thay đổi thành "floor_area"
    @JsonProperty("direction")
    private String direction;
    @JsonProperty("level")
    private String level;
    @JsonProperty("rent_price")
    private Integer rentPrice; // Thay đổi thành "rent_price"
    @JsonProperty("rent_price_description")
    private String rentPriceDescription;
    @JsonProperty("service_fee")
    private String serviceFee; // Thay đổi thành "service_fee"
    @JsonProperty("car_fee")
    private String carFee; // Thay đổi thành "car_fee"
    @JsonProperty("rent_areas")
    private List<Integer> rentAreas;
    @JsonProperty("motorbike_fee")
    private String motorbikeFee; // Thay đổi thành "motorbike_fee"
    @JsonProperty("overtime_fee")
    private String overtimeFee; // Thay đổi thành "overtime_fee"
    @JsonProperty("water_fee")
    private String waterFee; // Thay đổi thành "water_fee"
    @JsonProperty("electricity_fee")
    private String electricityFee; // Thay đổi thành "electricity_fee"
    @JsonProperty("decoration_time")
    private String decorationTime; // Thay đổi thành "decoration_time"
    @JsonProperty("brokerage_fee")
    private BigDecimal brokerageFee; // Thay đổi thành "brokerage_fee"
    @JsonProperty("link_of_building")
    private String linkOfBuilding; // Thay đổi thành "link_of_building"
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
}
