package io.github.matheus_fsantos.jp_users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class JpUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpUsersApplication.class, args);
	}

}
