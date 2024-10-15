package com.sparta.trelloproject.domain.notification.controller;

import com.sparta.trelloproject.domain.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/slack")
    public String sendSlackNotification(@RequestBody String message) {
        notificationService.sendSlackNotification(message);
        return "슬랙 알림 보냄!";
    }

    @PostMapping("/discord")
    public String sendDiscordNotification(@RequestBody String message) {
        notificationService.sendDiscordNotification(message);
        return "디스코드 알림 보냄!";
    }
}