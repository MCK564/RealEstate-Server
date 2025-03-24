package com.example.mck.notification_service.controller;

import com.example.mck.notification_service.models.Notification;
import com.example.mck.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/notices")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/{userToId}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Notification> getNotifications(@PathVariable("userToId") Long userToId) {
        return notificationService.findAllByUserIdTo(userToId);
    }

    @PostMapping("/mark-read/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void markNotificationAsRead(@PathVariable("id") String id) {
        notificationService.markNotificationAsRead(id);
    }

}
