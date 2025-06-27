package io.github.matheus_fsantos.jp_users.utils;

import io.github.matheus_fsantos.jp_users.dto.RequestUserDTO;
import io.github.matheus_fsantos.jp_users.dto.ResponseUserDTO;
import io.github.matheus_fsantos.jp_users.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public ResponseUserDTO toResponseUserDto(User user) {
        return new ResponseUserDTO(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getFirstName(),
                user.getLastName(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public User toUser(RequestUserDTO requestUserDto) {
        return new User(
                requestUserDto.firstName(),
                requestUserDto.lastName(),
                requestUserDto.email(),
                requestUserDto.password()
        );
    }

    public void updateUserFromDto(RequestUserDTO requestUserDto, User user) {
        user.setFirstName(requestUserDto.firstName());
        user.setLastName(requestUserDto.lastName());
        user.setPassword(requestUserDto.password());
        user.setEmail(requestUserDto.email());
    }
}
