package org.example.listingservice.repositories;

import jakarta.transaction.Transactional;
import org.example.listingservice.models.RentArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RentAreaRepository extends JpaRepository<RentArea,Long> {
    List<RentArea> findAllByBuilding_Id(Long buildingId);
    @Transactional
    void deleteAllByBuilding_Id(Long buildingId);

}
