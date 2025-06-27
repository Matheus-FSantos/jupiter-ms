package io.github.matheus_fsantos.jp_discovery_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class JpDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpDiscoveryServiceApplication.class, args);
	}

}
