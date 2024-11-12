package org.example.listingservice.builders;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Data
public class BuildingSearchBuilder {
    private String name;
    private String street;
    private String ward;
    private String district;
    private Integer numberOfBasement;
    private Double floorArea;
    private Integer rentPriceFrom;
    private Integer rentPriceTo;
    private Integer rentAreaFrom;
    private Integer rentAreaTo;
    private String managerName;
    private String managerPhoneNumber;
    private String ownerName;
    private Integer status;
    private List<String> type;
}
