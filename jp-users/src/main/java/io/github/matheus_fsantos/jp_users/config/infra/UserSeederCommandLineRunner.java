package io.github.matheus_fsantos.jp_users.config.infra;

import io.github.matheus_fsantos.jp_users.dto.RequestUserDTO;
import io.github.matheus_fsantos.jp_users.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("dev")
public class UserSeederCommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(UserSeederCommandLineRunner.class);
    private final UserService userService;

    public UserSeederCommandLineRunner(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public CommandLineRunner runUserSeeder () {
        return args -> {
            UserSeederCommandLineRunner.logger.info("Starting UserSeederCommandLineRunner...");
            RequestUserDTO user1 = new RequestUserDTO("Alice", "Smith", "alice.smith@example.com", "password123");
            RequestUserDTO user2 = new RequestUserDTO("Bob", "Johnson", "bob.johnson@example.com", "securepass");
            RequestUserDTO user3 = new RequestUserDTO("Charlie", "Brown", "charlie.brown@example.com", "peanuts");
            List<RequestUserDTO> requestUsersList = Arrays.asList(user1, user2, user3);

            requestUsersList.forEach(user -> {
                final String fullName = user.firstName() + " " + user.lastName();

                try {
                    userService.save(user);
                    logger.info("User '{}' saved successfully.", fullName);
                } catch (Exception e) {
                    logger.warn("Error saving user: {}", e.getMessage(), e);
                }
            });

            logger.info("UserSeederCommandLineRunner finished. \uD83D\uDE80 \uD83D\uDE80");
        };
    }
}
