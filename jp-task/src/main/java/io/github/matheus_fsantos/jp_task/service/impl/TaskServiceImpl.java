package io.github.matheus_fsantos.jp_task.service.impl;

import feign.FeignException;
import io.github.matheus_fsantos.jp_task.client.user.UserFeignClient;
import io.github.matheus_fsantos.jp_task.dto.RequestTaskDTO;
import io.github.matheus_fsantos.jp_task.dto.user.ResponseUserDTO;
import io.github.matheus_fsantos.jp_task.exception.TaskNotFoundException;
import io.github.matheus_fsantos.jp_task.model.Task;
import io.github.matheus_fsantos.jp_task.repository.TaskRepository;
import io.github.matheus_fsantos.jp_task.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService<Task, RequestTaskDTO, UUID> {
    private final TaskRepository taskRepository;
    private final UserFeignClient userFeignClient;

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserFeignClient userFeignClient) {
        this.taskRepository = taskRepository;
        this.userFeignClient = userFeignClient;
    }

    @Override
    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    @Override
    public Task findById(UUID id) {
        return this.taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public void save(RequestTaskDTO requestTaskDTO) {
        ResponseUserDTO taskOwner = this.userFeignClient.findById(
                UUID.fromString(requestTaskDTO.ownerId())
        ).getBody();

        TaskServiceImpl.logger.info("User found: {}", taskOwner);
    }

    @Override
    public void update(RequestTaskDTO requestTaskDTO, UUID uuid) { }

    @Override
    public void delete(UUID uuid) { }
}
