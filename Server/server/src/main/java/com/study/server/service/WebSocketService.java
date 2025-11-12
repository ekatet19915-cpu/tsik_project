package com.study.server.service;

import com.study.server.dto.WebSocketMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // 1. Чат в группе
    public void sendChatMessage(Long groupId, WebSocketMessage message) {
        messagingTemplate.convertAndSend("/topic/group/" + groupId + "/chat", message);
    }

    // 2. Обновление задач
    public void sendTaskUpdate(Long groupId, WebSocketMessage message) {
        messagingTemplate.convertAndSend("/topic/group/" + groupId + "/tasks", message);
    }

    // 3. Вход/выход участника
    public void sendMemberUpdate(Long groupId, WebSocketMessage message) {
        messagingTemplate.convertAndSend("/topic/group/" + groupId + "/members", message);
    }

    // 4. Личное уведомление
    public void sendUserNotification(Long userId, WebSocketMessage message) {
        messagingTemplate.convertAndSendToUser(userId.toString(), "/notifications", message);
    }
}
