package org.example.listingservice.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="buildingimage")
@Builder
public class BuildingImage extends  BaseEntity{
    public static int MAXIMUM_IMAGES_PER_BUILDING = 5;

    @Column(name="image_url",length=255)
    @JsonProperty("image_url")
    private String imageUrl;

    @Column(name="description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="building_id")
    private Building building;
}
