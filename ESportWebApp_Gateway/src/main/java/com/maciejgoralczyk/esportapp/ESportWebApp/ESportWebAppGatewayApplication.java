package com.maciejgoralczyk.esportapp.ESportWebApp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
@EnableDiscoveryClient
public class ESportWebAppGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(ESportWebAppGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder,
									  @Value("${PLAYER_SERVICE_APPNAME}") String playersServiceName,
									  @Value("${ORGANIZATION_SERVICE_APPNAME}") String organizationsServiceName,
									  @Value("${GATEWAY_HOST}") String host
	) {
		return builder
				.routes()
				.route("player-service", r -> r
						.host(host)
						.and()
						.path("/api/players/**", "/api/events/**")
						.uri("lb://" + playersServiceName))
				.route("organization-service", r -> r
						.host(host)
						.and()
						.path("/api/organizations/**")
						.uri("lb://" + organizationsServiceName))
				.build();
	}
}
