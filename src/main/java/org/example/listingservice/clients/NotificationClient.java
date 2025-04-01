package org.example.listingservice.clients;


import org.example.listingservice.models.Notification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value="notification-service", url="http://localhost:8081")
public class NotificationClient {
    @RequestMapping(method = RequestMethod.GET, value="/api/v1/notices")
    public List<Notification> getNotifications() {
        return List.of();
    }
}
