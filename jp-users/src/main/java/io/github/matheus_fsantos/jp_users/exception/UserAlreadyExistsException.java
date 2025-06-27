package io.github.matheus_fsantos.jp_users.exception;

import io.github.matheus_fsantos.jp_users.constants.UserExceptionConstants;

import java.util.UUID;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(UUID id) {
        super(String.format("%s with id: %s", UserExceptionConstants.USER_ALREADY_EXISTS, id));
    }

    public UserAlreadyExistsException() {
        super(UserExceptionConstants.USER_ALREADY_EXISTS);
    }
}
