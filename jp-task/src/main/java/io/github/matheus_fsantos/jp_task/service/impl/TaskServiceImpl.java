package io.github.matheus_fsantos.jp_task.service.impl;

import io.github.matheus_fsantos.jp_task.client.user.UserFeignClient;
import io.github.matheus_fsantos.jp_task.dto.RequestTaskDTO;
import io.github.matheus_fsantos.jp_task.dto.ResponseTaskDTO;
import io.github.matheus_fsantos.jp_task.dto.user.ResponseUserDTO;
import io.github.matheus_fsantos.jp_task.exception.TaskNotFoundException;
import io.github.matheus_fsantos.jp_task.model.Task;
import io.github.matheus_fsantos.jp_task.repository.TaskRepository;
import io.github.matheus_fsantos.jp_task.service.TaskService;
import io.github.matheus_fsantos.jp_task.utils.HateoasLinkBuilder;
import io.github.matheus_fsantos.jp_task.utils.mapper.TaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService<ResponseTaskDTO, RequestTaskDTO, UUID> {
    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    private final UserFeignClient userFeignClient;
    private final HateoasLinkBuilder hateoasLinkBuilder;

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    public TaskServiceImpl(TaskMapper taskMapper, TaskRepository taskRepository, UserFeignClient userFeignClient, HateoasLinkBuilder hateoasLinkBuilder) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
        this.userFeignClient = userFeignClient;
        this.hateoasLinkBuilder = hateoasLinkBuilder;
    }

    @Override
    public List<ResponseTaskDTO> findAll() {
        return this.taskRepository.findAll().stream()
                .map(task -> {
                    TaskServiceImpl.logger.debug("🔎 Search user with id: {}", task.getOwnerId());
                    ResponseUserDTO ownerDTO = this.userFeignClient.findById(task.getOwnerId()).getBody();
                    TaskServiceImpl.logger.debug("✅ User with ID {} validated via Feign Client.", task.getOwnerId());
                    ResponseTaskDTO taskDTO = this.taskMapper.toResponseTaskDTO(task, ownerDTO);
                    return this.hateoasLinkBuilder.addSelfLinkAlternative(taskDTO, taskDTO.getId());
                }).toList();
    }

    @Override
    public ResponseTaskDTO findById(UUID id) {
        Task task = this.taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        TaskServiceImpl.logger.debug("🔎 Search user with id: {}", task.getOwnerId());
        ResponseUserDTO ownerDTO = this.userFeignClient.findById(task.getOwnerId()).getBody();
        TaskServiceImpl.logger.debug("✅ User with ID {} validated via Feign Client.", task.getOwnerId());
        ResponseTaskDTO taskDTO = this.taskMapper.toResponseTaskDTO(task, ownerDTO);
        return this.hateoasLinkBuilder.addSelfLinkAlternative(taskDTO, taskDTO.getId());
    }

    @Override
    public void save(RequestTaskDTO requestTaskDTO) {
        TaskServiceImpl.logger.debug("🔎 Search user with id: {}", requestTaskDTO.ownerId());
        this.userFeignClient.findById(
                UUID.fromString(requestTaskDTO.ownerId())
        );
        TaskServiceImpl.logger.debug("✅ User with ID {} validated via Feign Client.", requestTaskDTO.ownerId());
        this.taskRepository.save(this.taskMapper.toTask(requestTaskDTO));
        TaskServiceImpl.logger.info("☑️ New task created by user with id {}", requestTaskDTO.ownerId());
    }

    @Override
    public void update(RequestTaskDTO requestTaskDTO, UUID uuid) { }

    @Override
    public void delete(UUID uuid) { }
}
