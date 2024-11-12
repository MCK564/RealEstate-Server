package org.example.listingservice.repositories;

import org.example.listingservice.models.Communication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunicationRepository extends JpaRepository<Communication,Long> {
    @Query("SELECT c FROM Communication c " +
            "LEFT JOIN c.building b " +
            "LEFT JOIN c.buyerRenter br " +
            "LEFT JOIN c.saler s " +
            "WHERE 1=1 AND c.phone LIKE %:keyword% OR c.note LIKE %:keyword% " +
            "OR b.name LIKE %:keyword% OR br.fullName LIKE %:keyword% OR s.fullName LIKE %:keyword%")
    Page<Communication> findByKeyword(String keyword, Pageable pageable);

    @Query("SELECT c FROM Communication c " +
            "LEFT JOIN c.building b " +
            "LEFT JOIN c.buyerRenter br " +
            "LEFT JOIN c.saler s " +
            "WHERE 1=1 AND (c.buyerRenter.id = :buyerRenterId OR c.saler.id = :salerId) " +
            "AND (c.phone LIKE %:keyword% OR c.note LIKE %:keyword% " +
            "OR b.name LIKE %:keyword% OR br.fullName LIKE %:keyword% OR s.fullName LIKE %:keyword%)")
    Page<Communication> findByKeywordAndSaler_IdOrBuyerRenter_Id(String keyword, Pageable pageable, Long salerId, Long buyerRenterId);

    Page<Communication> findAllBySaler_IdOrBuyerRenter_IdOrBuilding_Name(Long salerId, Long buyerRenterId, String buildingName,Pageable pageable);

    List<Communication> findAllByBuilding_Id(Long id);

}
