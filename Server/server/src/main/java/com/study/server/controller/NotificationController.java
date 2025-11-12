package com.study.server.controller;

import com.study.server.dto.WebSocketMessage;
import com.study.server.service.WebSocketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final WebSocketService webSocketService;

    public NotificationController(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    // 1. Чат группы
    @PostMapping("/group/{groupId}/chat")
    public String sendGroupChat(@PathVariable Long groupId, @RequestBody WebSocketMessage message) {
        webSocketService.sendChatMessage(groupId, message);
        return "Chat message sent to group " + groupId;
    }

    // 1.1 Новое сообщение
    @PostMapping("/group/{groupId}/message")
    public String sendNewMessage(@PathVariable Long groupId, @RequestBody WebSocketMessage message) {
        message.setType("NEW_MESSAGE"); // явный тип нового сообщения
        webSocketService.sendChatMessage(groupId, message);
        return "New message sent to group " + groupId;
    }

    // 2. Обновления задач
    @PostMapping("/group/{groupId}/tasks")
    public String sendTaskUpdate(@PathVariable Long groupId, @RequestBody WebSocketMessage message) {
        webSocketService.sendTaskUpdate(groupId, message);
        return "Task update sent to group " + groupId;
    }

    // 3. Вход/выход участников
    @PostMapping("/group/{groupId}/members")
    public String sendMemberUpdate(@PathVariable Long groupId, @RequestBody WebSocketMessage message) {
        webSocketService.sendMemberUpdate(groupId, message);
        return "Members update sent to group " + groupId;
    }

    // 4. Личные уведомления
    @PostMapping("/user/{userId}/notifications")
    public String sendUserNotification(@PathVariable Long userId, @RequestBody WebSocketMessage message) {
        webSocketService.sendUserNotification(userId, message);
        return "Notification sent to user " + userId;
    }

    // Тестовые GET-запросы
    @GetMapping("/group/{groupId}/test")
    public String testGroup(@PathVariable Long groupId) {
        WebSocketMessage message = new WebSocketMessage();
        message.setType("CHAT");
        message.setContent("Hello group " + groupId + "!");
        message.setGroupId(groupId);
        webSocketService.sendChatMessage(groupId, message);
        return "Test message sent to group " + groupId;
    }

    @GetMapping("/user/{userId}/test")
    public String testUser(@PathVariable Long userId) {
        WebSocketMessage message = new WebSocketMessage();
        message.setType("NOTIFICATION");
        message.setContent("Hello user " + userId + "!");
        message.setUserId(userId);
        webSocketService.sendUserNotification(userId, message);
        return "Test message sent to user " + userId;
    }
}
