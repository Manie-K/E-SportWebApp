package com.maciejgoralczyk.ESportWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ESportWebAppPlayerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ESportWebAppPlayerApplication.class, args);
	}
}
