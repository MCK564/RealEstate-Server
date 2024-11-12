package org.example.listingservice.repositories;

import org.example.listingservice.models.BuildingImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BuildingImageRepository extends JpaRepository<BuildingImage,Long> {
    List<BuildingImage> findAllByBuilding_Id(Long buildingId);

    @Transactional
    void deleteAllByBuilding_Id(Long id);
}
