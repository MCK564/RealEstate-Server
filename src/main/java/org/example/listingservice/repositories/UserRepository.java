package org.example.listingservice.repositories;


import org.example.listingservice.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByPhone (String phone);
    Optional<User> findByPhone(String phone);

    @Query("SELECT u from User u WHERE 1=1 AND ( "+
        "u.fullName LIKE %:keyword% "+
    "OR u.phone = :keyword "+
    "OR u.email LIKE %:keyword% )")
    Page<User> findAll(@Param("keyword")String keyword, Pageable pageable);

    Page<User> findAllByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneEqualsIgnoreCase(String keyword1, String keyword2,String phone, Pageable pageable);

    boolean existsById(Long id);
    List<User> findAllByIdIsIn(List<Long> ids);




}
