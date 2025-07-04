package io.github.matheus_fsantos.jp_task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record PutRequestTaskDTO(
    @Length(min = 5, max = 160, message = "Title must be between 5 and 160 characters")
    String title,

    @NotBlank(message = "Description is mandatory")
    @Length(min = 5, max = 255, message = "Description must be between 5 and 255 characters")
    String description,

    @Pattern(regexp = "TODO|IN_PROGRESS|DONE", message = "Status must be TODO, IN_PROGRESS or DONE")
    String status,

    @Pattern(regexp = "LOW|MEDIUM|HIGH|CRITICAL", message = "Priority must be LOW, MEDIUM, HIGH or CRITICAL")
    String priority
) { }
