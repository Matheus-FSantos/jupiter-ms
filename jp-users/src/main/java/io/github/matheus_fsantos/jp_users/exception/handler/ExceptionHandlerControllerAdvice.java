package io.github.matheus_fsantos.jp_users.exception.handler;

import io.github.matheus_fsantos.jp_users.constants.UserExceptionConstants;
import io.github.matheus_fsantos.jp_users.dto.ExceptionResponseDTO;
import io.github.matheus_fsantos.jp_users.exception.UserAlreadyExistsException;
import io.github.matheus_fsantos.jp_users.exception.UserNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        return ResponseEntity.status(UserExceptionConstants.USER_NOT_FOUND_STATUS).body(
                new ExceptionResponseDTO(
                        userNotFoundException.getMessage(),
                        UserExceptionConstants.USER_NOT_FOUND_STATUS,
                        LocalDateTime.now(),
                        "jp-users"
                )
        );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUserAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException) {
        return ResponseEntity.status(UserExceptionConstants.USER_ALREADY_EXISTS_STATUS).body(
                new ExceptionResponseDTO(
                        userAlreadyExistsException.getMessage(),
                        UserExceptionConstants.USER_ALREADY_EXISTS_STATUS,
                        LocalDateTime.now(),
                        "jp-users"
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDTO> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return ResponseEntity.status(UserExceptionConstants.TO_VALIDATION_ERROR_STATUS).body(
                new ExceptionResponseDTO(
                        methodArgumentNotValidException.getBindingResult().getAllErrors().stream().findFirst().get().getDefaultMessage(), /* get the first validation message in the "stack" */
                        UserExceptionConstants.TO_VALIDATION_ERROR_STATUS,
                        LocalDateTime.now(),
                        "jp-users"
                )
        );
    }
}
