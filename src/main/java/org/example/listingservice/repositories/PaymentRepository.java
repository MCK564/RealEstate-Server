package org.example.listingservice.repositories;

import org.example.listingservice.models.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAllByUser_IdAndStatus(Long userId, int status, Sort sort);

    @Query("SELECT p FROM Payment p WHERE FUNCTION('MONTH', p.createdAt) = :month AND FUNCTION('YEAR', p.createdAt) = :year")
    Page<Payment> findAllByMonthAndYear(@Param("month") int month, @Param("year") int year, Pageable pageable);

    @Query("SELECT SUM(p.money) FROM Payment p WHERE p.status = :status AND month(p.createdAt) = :month AND year(p.createdAt) =:year")
    Double getTotalRevenueByStatus (@Param("status") Integer status ,@Param("year") int year,@Param("month") int month);
    @Query("SELECT p FROM Payment p WHERE FUNCTION('MONTH', p.createdAt) = :month AND FUNCTION('YEAR', p.createdAt) = :year")
    List<Payment> findAllByMonthAndYear(@Param("month") int month, @Param("year") int year);

}
