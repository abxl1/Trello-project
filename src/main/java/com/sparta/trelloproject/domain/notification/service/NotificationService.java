package com.sparta.trelloproject.domain.notification.service;

import com.sparta.trelloproject.domain.notification.config.NotificationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationService {

    private final RestTemplate restTemplate;
    private final NotificationProperties notificationProperties;

    public NotificationService(RestTemplate restTemplate, NotificationProperties notificationProperties) {
        this.restTemplate = restTemplate;
        this.notificationProperties = notificationProperties;
    }

    public void sendSlackNotification(String message) {
        Map<String, String> payload = new HashMap<>();
        payload.put("text", message);
        restTemplate.postForObject(notificationProperties.getSlackUrl(), payload, String.class);
    }

    public void sendDiscordNotification(String message) {
        Map<String, String> payload = new HashMap<>();
        payload.put("content", message);
        restTemplate.postForObject(notificationProperties.getDiscordUrl(), payload, String.class);
    }
}