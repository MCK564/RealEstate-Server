package com.example.mck.notification_service.service;

import com.example.mck.notification_service.models.Notification;
import com.example.mck.notification_service.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    public List<Notification> findAllByUserIdTo(Long userIdTo) {
        return notificationRepository.findAllByUserIdTo(userIdTo, Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    public Boolean markNotificationAsRead(String id) {
        Optional<Notification> existingNotification = notificationRepository.findById(id);
        if (existingNotification.isPresent()) {
            existingNotification.get().setRead(true);
            notificationRepository.save(existingNotification.get());
            return true;
        }
        return false;
    }




}
