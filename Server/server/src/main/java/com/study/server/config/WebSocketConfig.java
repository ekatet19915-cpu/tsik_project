package com.study.server.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // Подписки: /topic/...
        config.setApplicationDestinationPrefixes("/app"); // Отправка: /app/...
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // Подключение: ws://localhost:8080/ws
                .withSockJS(); // Поддержка старых браузеров (и JavaFX)
    }
}