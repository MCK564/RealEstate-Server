package com.example.mck.notification_service.models;


import com.example.mck.notification_service.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value="notification")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Notification {
    @Id
    private String id;
    private String message;

    private NotificationType notificationType;

    private Long userIdFrom;
    private Long userIdTo;
    private String fullNameFrom;
    private String fullNameTo;
    private String locationFrom;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder.Default
    private Boolean read = false;
}
