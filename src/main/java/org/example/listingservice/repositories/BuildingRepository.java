package org.example.listingservice.repositories;

import org.example.listingservice.models.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building,Long>  {
    List<Building> findAllByNameLike(String name);
    List<Building> findAllByStatus(int status);
    Page<Building> findAll(Specification<Building> spec, Pageable pageable);

    Page<Building> findAll(Pageable pageable);
    List<Building> findByUser_Id(Long id);

    @Query("SELECT b from Building b "+
    "LEFT JOIN FETCH b.likes "+
    "GROUP BY b "+
    "ORDER BY COUNT(b.likes) DESC ")
    List<Building> findTop30BuildingsWithMostLikes();

    List<Building> findAllByDistrictOrWardLikeOrStreetLikeOrderByCreatedDateDesc(String district, String ward, String street);

}
