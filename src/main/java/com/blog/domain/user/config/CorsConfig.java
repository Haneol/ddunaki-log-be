package com.blog.domain.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173","http://localhost:8080","http://haneol-test.kro.kr"));
        configuration.addAllowedMethod(HttpMethod.GET);
        configuration.addAllowedMethod(HttpMethod.POST);
        configuration.addAllowedMethod(HttpMethod.HEAD);
        configuration.addAllowedMethod(HttpMethod.PUT);
        configuration.addAllowedMethod(HttpMethod.DELETE);
        configuration.addAllowedMethod(HttpMethod.TRACE);
        configuration.addAllowedMethod(HttpMethod.OPTIONS);
        configuration.setAllowedHeaders(List.of("Authorization","Cache-Control","Content-Type"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }
}
