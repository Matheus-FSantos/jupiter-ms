package io.github.matheus_fsantos.jp_task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.matheus_fsantos.jp_task.dto.user.ResponseUserDTO;
import io.github.matheus_fsantos.jp_task.model.Priority;
import io.github.matheus_fsantos.jp_task.model.Status;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "tasks", itemRelation = "task")
public class ResponseTaskDTO extends RepresentationModel<ResponseTaskDTO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private UUID id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private ResponseUserDTO owner;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime updatedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime startedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime finishedAt;
}
