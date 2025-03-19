package org.example.listingservice.configurations;

import org.example.listingservice.interceptors.JwtAuthHandshakeInterceptor;
import org.example.listingservice.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @Bean
    public JwtAuthHandshakeInterceptor jwtAuthHandshakeInterceptor() {
        return new JwtAuthHandshakeInterceptor(jwtUtils, userDetailsService);
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        config.enableSimpleBroker("/chatroom", "user");
        config.setUserDestinationPrefix("/user");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .addInterceptors(jwtAuthHandshakeInterceptor())
                .withSockJS();
    }
}


// message are sent via WebSocket using STOMP (simple text oriented messaging protocol).