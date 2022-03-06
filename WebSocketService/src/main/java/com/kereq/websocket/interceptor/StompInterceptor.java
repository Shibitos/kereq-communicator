package com.kereq.websocket.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.kereq.communicator.common.dto.UserDTO;
import com.kereq.communicator.common.service.JWTService;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@Component
public class StompInterceptor implements ChannelInterceptor {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    private final JWTService jwtService;

    public StompInterceptor(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
            Optional<String> token = Objects.requireNonNull(accessor.getNativeHeader(TOKEN_HEADER)).stream().findFirst();
            if (token.isPresent() && token.get().startsWith(TOKEN_PREFIX)) {
                DecodedJWT decodedJWT = jwtService.verifyToken(token.get().replace(TOKEN_PREFIX, ""));
                String email = decodedJWT.getSubject();
                if (email != null) {
                    UserDTO userDetails = new UserDTO(decodedJWT.getClaim("id").asLong(), email, decodedJWT.getClaim("roles").asList(String.class));
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
                    accessor.setUser((Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                }
            }
        }
        return message;
    }
}
