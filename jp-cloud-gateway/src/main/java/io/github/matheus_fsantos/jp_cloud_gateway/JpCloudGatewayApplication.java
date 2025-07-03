package io.github.matheus_fsantos.jp_cloud_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class JpCloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpCloudGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator router(RouteLocatorBuilder builder) {
		return builder
				.routes()
					.route(r -> r.path(("/api/users/**"))
							.filters(f -> f
											.addRequestHeader("X-Forwarded-Proto", "http")
											.addRequestHeader("X-Forwarded-Host", "localhost:8080")
											.addRequestHeader("X-Forwarded-Port", "8080")
							)
							.uri("lb://jp-users"))
				.route(r -> r.path(("/api/tasks/**"))
						.filters(f -> f
								.addRequestHeader("X-Forwarded-Proto", "http")
								.addRequestHeader("X-Forwarded-Host", "localhost:8080")
								.addRequestHeader("X-Forwarded-Port", "8080")
						)
						.uri("lb://jp-tasks"))
				.build();
	}

}
