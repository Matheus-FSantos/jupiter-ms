package io.github.matheus_fsantos.jp_task.exception;

import io.github.matheus_fsantos.jp_task.constants.TaskExceptionConstants;

import java.util.UUID;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(UUID id) {
        super(String.format("%s with id: %s", TaskExceptionConstants.TASK_NOT_FOUND, id));
    }
}
