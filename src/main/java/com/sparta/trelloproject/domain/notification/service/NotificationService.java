package com.sparta.trelloproject.domain.notification.service;

import com.sparta.trelloproject.config.NotificationProperties;
import lombok.RequiredArgsConstructor;
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


//    댓글 생성 알림
    public void sendSlackNotification(String email, String cardId, String comment) {
        String message = String.format("%s님이 '%s' 카드에 댓글을 달았습니다: \"%s\"",
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


//    워크스페이스 생성알림
    public void sendWorkspaceCreationNotification(String email, String entityType, String entityId) {
        // 워크스페이스 생성 관련 알림 메시지
        String message = String.format("%s님이 %s번 %s를 생성했습니다.", email, entityId, entityType);

        Map<String, String> payload = new HashMap<>();
        payload.put("text", message);  // Slack에 메시지 보내기
        restTemplate.postForObject(notificationProperties.getSlackUrl(), payload, String.class);

        payload.clear();  // Discord 메시지로 보내기 위해 새로 준비
        payload.put("content", message);
        restTemplate.postForObject(notificationProperties.getDiscordUrl(), payload, String.class);
    }
//

//    보드 생성 알림
    public void sendBoardCreationNotification(String email, String workspaceId, String boardId, String boardTitle) {
        String message = String.format("%s님이 %s번 워크스페이스에 '%s'보드를 생성했습니다.", email, workspaceId, boardTitle);

    // 슬랙 알림 전송
        Map<String, String> slackPayload = new HashMap<>();
        slackPayload.put("text", message);
        restTemplate.postForObject(notificationProperties.getSlackUrl(), slackPayload, String.class);

    // 디스코드 알림 전송
        Map<String, String> discordPayload = new HashMap<>();
        discordPayload.put("content", message);
        restTemplate.postForObject(notificationProperties.getDiscordUrl(), discordPayload, String.class);
}

//   리스트 생성 알림
    public void sendListCreationNotification(String email, String boardId, String listId, String listTitle) {
        String message = String.format("%s님이 %s번 보드에 %s번 리스트(%s)를 생성했습니다.", email, boardId, listId, listTitle);

        Map<String, String> payload = new HashMap<>();
        payload.put("text", message);
        restTemplate.postForObject(notificationProperties.getSlackUrl(), payload, String.class);

        payload.clear();
        payload.put("content", message);
        restTemplate.postForObject(notificationProperties.getDiscordUrl(), payload, String.class);
    }

    // 카드 생성 알림
    public void sendCardCreationNotification(String email, Long boardId, Long listId, Long cardId) {
        String message = String.format("%s님이 %s번 보드에 %s번 리스트에서 %s번 카드를 생성하였습니다.", email, boardId, listId, cardId);

        Map<String, String> slackPayload = new HashMap<>();
        slackPayload.put("text", message);
        restTemplate.postForObject(notificationProperties.getSlackUrl(), slackPayload, String.class);

        Map<String, String> discordPayload = new HashMap<>();
        discordPayload.put("content", message);
        restTemplate.postForObject(notificationProperties.getDiscordUrl(), discordPayload, String.class);
    }

}