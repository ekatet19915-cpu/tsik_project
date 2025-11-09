package com.study.server.controller;

import com.study.server.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    // Отправка сообщения
    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessage message) {
        messagingTemplate.convertAndSend(
                "/topic/chat/" + message.groupId(),
                message
        );
    }

    // Пользователь вошёл
    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage message) {
        messagingTemplate.convertAndSend(
                "/topic/activity/" + message.groupId(),
                new ChatMessage(
                        message.sender() + " pripojil sa",
                        "System",
                        message.groupId(),
                        "JOIN"
                )
        );
    }
}