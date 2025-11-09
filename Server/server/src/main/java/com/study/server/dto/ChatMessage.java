package com.study.server.dto;

public record ChatMessage(
        String content,
        String sender,
        Long groupId,
        String type // "CHAT", "JOIN", "LEAVE"
) {}