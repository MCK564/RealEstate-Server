package com.example.mck.notification_service.repositories;

import com.example.mck.notification_service.models.Notification;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

    List<Notification> findAllByUserIdTo(Long userIdTo, Sort sort);
    Optional<Notification> findByUserIdTo(Long userIdTo);
}
