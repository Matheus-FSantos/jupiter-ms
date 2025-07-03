package io.github.matheus_fsantos.jp_task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ExceptionResponseDTO(
    String message,
    HttpStatus status,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    LocalDateTime exceptionTime,
    String service
) { }
