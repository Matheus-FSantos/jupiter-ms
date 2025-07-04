package io.github.matheus_fsantos.jp_task.utils.mapper;

import io.github.matheus_fsantos.jp_task.dto.PostRequestTaskDTO;
import io.github.matheus_fsantos.jp_task.dto.PutRequestTaskDTO;
import io.github.matheus_fsantos.jp_task.dto.ResponseTaskDTO;
import io.github.matheus_fsantos.jp_task.dto.user.ResponseUserDTO;
import io.github.matheus_fsantos.jp_task.model.Priority;
import io.github.matheus_fsantos.jp_task.model.Status;
import io.github.matheus_fsantos.jp_task.model.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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

    public Task toTask(PostRequestTaskDTO postRequestTaskDTO) {
        return new Task(
                UUID.fromString(postRequestTaskDTO.ownerId()),
                postRequestTaskDTO.title(),
                postRequestTaskDTO.description(),
                Status.TODO,
                Priority.valueOf(postRequestTaskDTO.priority())
        );
    }

    public void updateTaskFromDTO(Task task, PutRequestTaskDTO putRequestTaskDTO) {
        task.setDescription(putRequestTaskDTO.description());
        task.setUpdatedAt(LocalDateTime.now());

        if(putRequestTaskDTO.title() != null && !putRequestTaskDTO.title().isBlank())
            task.setTitle(putRequestTaskDTO.title());

        if(putRequestTaskDTO.status() != null && !putRequestTaskDTO.status().isBlank()) { /* if field is "" also not working -> ![...].isBlank() */
            task.setStatus(Status.valueOf(putRequestTaskDTO.status()));
            if(task.getStatus() == Status.DONE) {
                if(task.getStartedAt() == null)
                    task.setStartedAt(LocalDateTime.now()); /* need an initial start date */
                task.setFinishedAt(LocalDateTime.now());
            }

            if(task.getStatus() == Status.IN_PROGRESS)
                task.setStartedAt(LocalDateTime.now());
        }

        if(putRequestTaskDTO.priority() != null && !putRequestTaskDTO.priority().isBlank())
            task.setPriority(Priority.valueOf(putRequestTaskDTO.priority()));
    }
}
