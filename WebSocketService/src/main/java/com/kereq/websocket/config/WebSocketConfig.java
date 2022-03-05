package com.kereq.websocket.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kereq.common.dto.UserDTO;
import com.kereq.common.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.security.Principal;
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

    @Value("${destination.prefix}")
    private String destinationPrefix;

    @Value("${stomp.broker.relay}")
    private String stompBrokerRelay;

    @Autowired
    private JWTService jwtService;

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry config) {
        config
                .enableStompBrokerRelay(stompBrokerRelay)
                .setRelayHost(host).setRelayPort(port).setSystemLogin(userName).setSystemPasscode(password)
                .setClientLogin(userName).setClientPasscode(password);
        config.setApplicationDestinationPrefixes(destinationPrefix);
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint(endpoint).setAllowedOrigins("http://localhost:4200").withSockJS();
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
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    List<String> authorization = accessor.getNativeHeader("X-Authorization");

                    String accessToken = authorization.get(0).split(" ")[1];
                    DecodedJWT decodedJWT = jwtService.verifyToken(accessToken);
                    String email = decodedJWT.getSubject();
                    if (email != null) {
                        UserDTO userDetails = new UserDTO(decodedJWT.getClaim("id").asLong(), email, decodedJWT.getClaim("roles").asList(String.class));
                        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
                        accessor.setUser((Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                    }
                }
                return message;
            }
        });
    }
}
