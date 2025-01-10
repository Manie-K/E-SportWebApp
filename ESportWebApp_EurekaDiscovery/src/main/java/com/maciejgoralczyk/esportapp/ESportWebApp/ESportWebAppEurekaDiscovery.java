package com.maciejgoralczyk.esportapp.ESportWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ESportWebAppEurekaDiscovery {

	public static void main(String[] args) {
		SpringApplication.run(ESportWebAppEurekaDiscovery.class, args);
	}

}
