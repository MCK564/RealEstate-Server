package org.example.listingservice.repositories;

import org.example.listingservice.models.Love;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoveRepository extends JpaRepository<Love,Long> {
    List<Love> findAllByUser_Id(Long userId);
    List<Love> findAllByBuilding_Id(Long buildingId);
    Love findByUser_IdAndBuilding_Id(Long userId,Long BuildingId);
    Boolean existsByUser_IdAndBuilding_Id(Long userId, Long BuildingId);
}
