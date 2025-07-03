package io.github.matheus_fsantos.jp_task.config.infra;

import io.github.matheus_fsantos.jp_task.dto.RequestTaskDTO;
import io.github.matheus_fsantos.jp_task.model.Priority;
import io.github.matheus_fsantos.jp_task.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.UUID;

@Configuration
@Profile("dev")
public class TaskSeederCommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(TaskSeederCommandLineRunner.class);
    private final TaskService taskService;

    public TaskSeederCommandLineRunner(TaskService taskService) {
        this.taskService = taskService;
    }

    @Bean
    public CommandLineRunner runTaskSeeder() {
        return args -> {
            TaskSeederCommandLineRunner.logger.info("Starting TaskSeederCommandLineRunner...");
            RequestTaskDTO task1 = new RequestTaskDTO(UUID.randomUUID().toString(), "Task 1", "Description 1", Priority.LOW.toString());
            RequestTaskDTO task2 = new RequestTaskDTO(UUID.randomUUID().toString(), "Task 2", "Description 2", Priority.MEDIUM.toString());
            RequestTaskDTO task3 = new RequestTaskDTO(UUID.randomUUID().toString(), "Task 3", "Description 3", Priority.HIGH.toString());
            RequestTaskDTO task4 = new RequestTaskDTO(UUID.randomUUID().toString(), "Task 4", "Description 4", Priority.CRITICAL.toString());
            List<RequestTaskDTO> requestTasksList = List.of(task1, task2, task3, task4);

            requestTasksList.forEach(task -> {
                try {
                    this.taskService.save(task); /* not working */
                } catch (Exception e) {
                    TaskSeederCommandLineRunner.logger.warn("Error saving task: {}", e.getMessage(), e);
                }
            });

            TaskSeederCommandLineRunner.logger.info("TaskSeederCommandLineRunner finished. \uD83D\uDE80 \uD83D\uDE80");
        };
    }
}
