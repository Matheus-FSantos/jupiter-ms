package io.github.matheus_fsantos.jp_task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record PostRequestTaskDTO(
        @NotBlank(message = "Owner id is mandatory")
        @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$", message = "Invalid UUID format")
        String ownerId,

        @NotBlank(message = "Title is mandatory")
        @Length(min = 5, max = 160, message = "Title must be between 5 and 160 characters")
        String title,

        @NotBlank(message = "Description is mandatory")
        @Length(min = 5, max = 255, message = "Description must be between 5 and 255 characters")
        String description,

        @NotBlank(message = "Status is mandatory")
        @Pattern(regexp = "LOW|MEDIUM|HIGH|CRITICAL", message = "Priority must be LOW, MEDIUM, HIGH or CRITICAL")
        String priority
) { }
