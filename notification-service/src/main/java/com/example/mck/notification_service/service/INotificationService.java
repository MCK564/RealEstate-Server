package com.example.mck.notification_service.service;

import com.example.mck.notification_service.models.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface INotificationService {
    List<Notification> findAllByUserIdTo(Long userIdTo);
    Boolean markNotificationAsRead(String id);

}
