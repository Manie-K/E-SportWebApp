package com.maciejgoralczyk.esportapp.ESportWebApp.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration{
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder,
                                      @Value("${ESPORT_GATEWAY_HOST}") String gatewayUrl,
                                      @Value("${ESPORT_PLAYER_SERVICE_URL}") String playersUrl,
                                      @Value("${ESPORT_ORGANIZATION_SERVICE_URL}") String organizationsUrl
    ) {
        return builder.routes()
                .route("player-service", r -> r
                        .path("/api/players/**", "/api/events/**")
                        .uri(playersUrl))
                .route("organization-service", r -> r
                        .path("/api/organizations/**")
                        .uri(organizationsUrl))
                .build();
    }
}
