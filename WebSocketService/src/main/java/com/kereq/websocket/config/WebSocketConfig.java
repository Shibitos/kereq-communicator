package com.kereq.websocket.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kereq.websocket.interceptor.StompInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${spring.rabbitmq.username}")
    private String userName;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.addresses}")
    private String host;

    @Value("${spring.rabbitmq.stomp-port}")
    private int port;

    @Value("${endpoint}")
    private String endpoint;

    @Value("${frontend.url}")
    private String frontendUrl;

    @Autowired
    private StompInterceptor stompInterceptor;

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry config) {
        config
                .enableStompBrokerRelay("/topic/", "/queue/")
                .setRelayHost(host).setRelayPort(port)
                .setSystemLogin(userName).setSystemPasscode(password)
                .setClientLogin(userName).setClientPasscode(password);
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint(endpoint).setAllowedOrigins(frontendUrl).withSockJS();
    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        var resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);

        var converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver);

        messageConverters.add(converter);

        return false;
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompInterceptor);
    }
}
