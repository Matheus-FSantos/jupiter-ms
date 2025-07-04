package io.github.matheus_fsantos.jp_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class JpTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpTaskApplication.class, args);
	}

}
