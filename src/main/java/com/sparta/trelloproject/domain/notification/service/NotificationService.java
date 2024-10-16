package com.sparta.trelloproject.domain.notification.service;

import com.sparta.trelloproject.config.NotificationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationService {

//    slack , discord 에 요청보내기위함
//    apllication.properties 에서 알림 url 읽어오기위함
    private final RestTemplate restTemplate;
    private final NotificationProperties notificationProperties;

    public NotificationService(RestTemplate restTemplate, NotificationProperties notificationProperties) {
        this.restTemplate = restTemplate;
        this.notificationProperties = notificationProperties;
    }

//    email : 댓글 작성자
//    cardId :  댓글 카드 번호
//    comment : 댓글 내용

    public void sendSlackNotification(String email, String cardId, String comment) {
        String message = String.format("% s님이 '%s' 카드에 댓글을 달았습니다: \"%s\"",
                email, cardId, comment);

        Map<String, String> payload = new HashMap<>();
        payload.put("text", message);
        restTemplate.postForObject(notificationProperties.getSlackUrl(), payload, String.class);
    }

    public void sendDiscordNotification(String email , String cardId, String comment) {
        String message = String.format("%s님이 '%s' 카드에 댓글을 달았습니다: \"%s\"",
                email , cardId, comment);

        Map<String, String> payload = new HashMap<>();
        payload.put("content", message);
        restTemplate.postForObject(notificationProperties.getDiscordUrl(), payload, String.class);
    }
}