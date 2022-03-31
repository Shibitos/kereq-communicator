package com.kereq.gateway.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

//    private final String frontendUrl;
//
//    public SecurityConfig(@Value("${frontend.url}") String frontendUrl) {
//        this.frontendUrl = frontendUrl;
//    }
//
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        return http.csrf().disable().cors().disable().authorizeExchange().anyExchange().permitAll().and().build();
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); //TODO: think of it
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin(frontendUrl);
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        config.setMaxAge(3600L);
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration apiCorsConfiguration = new CorsConfiguration();
//        apiCorsConfiguration.setAllowCredentials(true);
//        apiCorsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
//        apiCorsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
//        apiCorsConfiguration.setAllowedMethods(Collections.singletonList("*"));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", apiCorsConfiguration);
//        return source;
//    }
}
