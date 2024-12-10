package com.maciejgoralczyk.esportapp.ESportWebApp.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration{
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("player-service", r -> r
                        .host("localhost:8081")
                        .and()
                        .path("/api/players/**", "/api/events/**")
                        .uri("http://localhost:8083"))
                .route("organization-service", r -> r
                        .host("localhost:8081")
                        .and()
                        .path("/api/organizations/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}
