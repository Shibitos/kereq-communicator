package com.kereq.websocket.config;

import com.kereq.common.service.JWTService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig extends com.kereq.common.config.SecurityConfig {

    private final JWTService jwtService;

    public SecurityConfig(JWTService jwtService, @Value("${frontend.url}") String frontendUrl) {
        super(jwtService, frontendUrl);
        this.jwtService = jwtService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/ws/**").permitAll();
        super.configure(http);
    }
}
