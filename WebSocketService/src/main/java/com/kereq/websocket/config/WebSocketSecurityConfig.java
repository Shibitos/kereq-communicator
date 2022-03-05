package com.kereq.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        //messages.anyMessage().authenticated();
        //TODO: test if in current situation requests without jwt will be rejected
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}
