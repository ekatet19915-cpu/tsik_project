package com.study.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Сервер может посылать на /topic и /queue
        config.enableSimpleBroker("/topic", "/queue");
        // Клиенты отправляют на /app/...
        config.setApplicationDestinationPrefixes("/app");
        // Для личных уведомлений через convertAndSendToUser(...)
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("http://127.0.0.1:5500", "http://localhost:5500")
                .withSockJS();
    }
}
