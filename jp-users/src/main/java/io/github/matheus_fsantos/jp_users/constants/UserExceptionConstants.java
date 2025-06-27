package io.github.matheus_fsantos.jp_users.constants;

import org.springframework.http.HttpStatus;

public class UserExceptionConstants {
    public static final String USER_NOT_FOUND = "User not found";
    public static final HttpStatus USER_NOT_FOUND_STATUS = HttpStatus.NOT_FOUND;

    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final HttpStatus USER_ALREADY_EXISTS_STATUS = HttpStatus.CONFLICT;

    public static final HttpStatus TO_VALIDATION_ERROR_STATUS = HttpStatus.UNPROCESSABLE_ENTITY;

}
