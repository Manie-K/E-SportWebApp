package com.maciejgoralczyk.esportapp.ESportWebApp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import java.util.logging.Logger;
import java.util.Set;
import org.springframework.http.*;


@SpringBootApplication
@EnableDiscoveryClient
public class ESportWebAppGatewayApplication {
	private static Logger logger = Logger.getLogger(ESportWebAppGatewayApplication.class.getName());
	public static void main(String[] args) {
		SpringApplication.run(ESportWebAppGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder,
									  @Value("${PLAYER_SERVICE_APPNAME}") String playersServiceName,
									  @Value("${ORGANIZATION_SERVICE_APPNAME}") String organizationsServiceName,
									  @Value("${GATEWAY_HOST}") String host
	) {
		logger.info("Player Service App Name: " + playersServiceName);
		logger.info("Organization Service App Name: " + organizationsServiceName);
		logger.info("Gateway Host: " + host);
		return builder
				.routes()
				.route("player-service", r -> r
						.path("/api/players/**", "/api/events/**")
						.uri("lb://" + playersServiceName))
				.route("organization-service", r -> r
						.path("/api/organizations/**")
						//.uri("lb://" + organizationsServiceName))
						.uri("http://organization-service-1:8080/api/organizations"))
				.build();
	}
/*
	@Bean
	public CorsWebFilter corsWebFilter() {

		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Collections.singletonList("*"));
		corsConfig.setMaxAge(3600L);
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
		corsConfig.addAllowedHeader("*");

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}*/

}
