package io.github.matheus_fsantos.jp_users.exception;

import io.github.matheus_fsantos.jp_users.constants.UserExceptionConstants;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID id) {
        super(String.format("%s with id: %s", UserExceptionConstants.USER_NOT_FOUND, id));
    }

    public UserNotFoundException(String email) {
        super(String.format("%s with e-mail: %s", UserExceptionConstants.USER_NOT_FOUND, email));
    }
}
