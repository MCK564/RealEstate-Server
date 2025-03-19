package org.example.listingservice.models;

import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "building")
@Builder
public class Building extends BaseEntity {
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<RentArea> rentAreas = new ArrayList<>();

    @OneToMany(mappedBy="building", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BuildingImage> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="owner_id")
    private User user;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Love> likes = new ArrayList<>();

    private String name;
    private String street;
    private String ward;
    private String district;
    private String structure;

    @Column(name="numberofbasement")
    private Integer numberOfBasement;

    @Column(name="floorarea")
    private Integer floorArea;
    private String direction;
    private String level;

    @Column(name = "rentprice")
    private Integer rentPrice;

    @Column(name = "rentpricedescription", columnDefinition = "TEXT")
    private String rentPriceDescription;

    @Column(name = "servicefee")
    private String serviceFee;

    @Column(name = "carfee")
    private String carFee;

    @Column(name = "motofee")
    private String motorbikeFee;

    @Column(name = "overtimefee")
    private String overtimeFee;

    @Column(name = "waterfee")
    private String waterFee;

    @Column(name = "electricityfee")
    private String electricityFee;
    private String deposit;
    private String payment;

    @Column(name = "renttime")
    private String rentTime;

    @Column(name = "decorationtime")
    private String decorationTime;

    @Column(name = "brokeragetee")
    private BigDecimal brokerageFee;

    @Column(name = "note")
    private String note;

    @Column(name = "linkofbuilding")
    private String linkOfBuilding;
    private String map;

    private String avatar;

    @Column(name = "managername")
    private String managerName;

    @Column(name = "managerphone")
    private String managerPhoneNumber;

    private Integer status;
    private String type;

    @Override
    public String toString(){
        return "";
    }
}
