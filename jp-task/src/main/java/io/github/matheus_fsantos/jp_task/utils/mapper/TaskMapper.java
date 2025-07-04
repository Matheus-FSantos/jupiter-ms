package io.github.matheus_fsantos.jp_task.utils.mapper;

import io.github.matheus_fsantos.jp_task.dto.RequestTaskDTO;
import io.github.matheus_fsantos.jp_task.dto.ResponseTaskDTO;
import io.github.matheus_fsantos.jp_task.dto.user.ResponseUserDTO;
import io.github.matheus_fsantos.jp_task.model.Priority;
import io.github.matheus_fsantos.jp_task.model.Status;
import io.github.matheus_fsantos.jp_task.model.Task;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TaskMapper {
    public ResponseTaskDTO toResponseTaskDTO(Task task, ResponseUserDTO userDTO) {
        return new ResponseTaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                userDTO,
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getStartedAt(),
                task.getFinishedAt()
        );
    }

    public Task toTask(RequestTaskDTO requestTaskDTO) {
        return new Task(
                UUID.fromString(requestTaskDTO.ownerId()),
                requestTaskDTO.title(),
                requestTaskDTO.description(),
                Status.TODO,
                Priority.valueOf(requestTaskDTO.priority())
        );
    }
}
