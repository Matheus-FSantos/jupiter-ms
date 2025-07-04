package io.github.matheus_fsantos.jp_task.exception.handler;

import feign.FeignException;
import io.github.matheus_fsantos.jp_task.constants.TaskExceptionConstants;
import io.github.matheus_fsantos.jp_task.dto.ExceptionResponseDTO;
import io.github.matheus_fsantos.jp_task.exception.TaskNotFoundException;
import io.github.matheus_fsantos.jp_task.utils.FeignErrorParser;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleTaskNotFoundException(TaskNotFoundException taskNotFoundException) {
        return ResponseEntity.status(TaskExceptionConstants.TASK_NOT_FOUND_STATUS).body(
                new ExceptionResponseDTO(
                        taskNotFoundException.getMessage(),
                        TaskExceptionConstants.TASK_NOT_FOUND_STATUS,
                        LocalDateTime.now(),
                        "jp-tasks"
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDTO> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return ResponseEntity.status(TaskExceptionConstants.TO_VALIDATION_ERROR_STATUS).body(
                new ExceptionResponseDTO(
                        methodArgumentNotValidException.getBindingResult().getAllErrors().stream().findFirst().get().getDefaultMessage(), /* get the first validation message in the "stack" */
                        TaskExceptionConstants.TO_VALIDATION_ERROR_STATUS,
                        LocalDateTime.now(),
                        "jp-tasks"
                )
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponseDTO> handleMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException) {
        return ResponseEntity.status(TaskExceptionConstants.TO_VALIDATION_ERROR_STATUS).body(
                new ExceptionResponseDTO(
                        httpMessageNotReadableException.getLocalizedMessage(), /* get the first validation message in the "stack" */
                        TaskExceptionConstants.TO_VALIDATION_ERROR_STATUS,
                        LocalDateTime.now(),
                        "jp-tasks"
                )
        );
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ExceptionResponseDTO> handleFeignException(FeignException feignException) {
        return ResponseEntity.status(TaskExceptionConstants.USER_FEIGN_NOT_FOUND_STATUS).body(
                new ExceptionResponseDTO(
                        FeignErrorParser.extractMessage(feignException),
                        TaskExceptionConstants.USER_FEIGN_NOT_FOUND_STATUS,
                        LocalDateTime.now(),
                        "jp-tasks"
                )
        );
    }
}
