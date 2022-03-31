package com.kereq.websocket.config;

import com.kereq.communicator.common.service.JWTService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig extends com.kereq.communicator.common.config.SecurityConfig {

    public SecurityConfig(JWTService jwtService) {
        super(jwtService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/ws/**").permitAll();
        super.configure(http);
    }
}
