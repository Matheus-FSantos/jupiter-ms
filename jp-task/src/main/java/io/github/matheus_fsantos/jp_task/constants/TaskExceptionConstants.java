package io.github.matheus_fsantos.jp_task.constants;

import org.springframework.http.HttpStatus;

public class TaskExceptionConstants {
    public static final String TASK_NOT_FOUND = "Task not found";
    public static final HttpStatus TASK_NOT_FOUND_STATUS = HttpStatus.NOT_FOUND;

    public static final String TASK_ALREADY_EXISTS = "Task already exists";
    public static final HttpStatus TASK_ALREADY_EXISTS_STATUS = HttpStatus.CONFLICT;

    public static final HttpStatus TO_VALIDATION_ERROR_STATUS = HttpStatus.UNPROCESSABLE_ENTITY;

}
