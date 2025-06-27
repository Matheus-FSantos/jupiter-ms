package io.github.matheus_fsantos.jp_users.service.impl;

import io.github.matheus_fsantos.jp_users.dto.RequestUserDTO;
import io.github.matheus_fsantos.jp_users.dto.ResponseUserDTO;
import io.github.matheus_fsantos.jp_users.exception.UserAlreadyExistsException;
import io.github.matheus_fsantos.jp_users.exception.UserNotFoundException;
import io.github.matheus_fsantos.jp_users.model.User;
import io.github.matheus_fsantos.jp_users.repository.UserRepository;
import io.github.matheus_fsantos.jp_users.service.UserService;
import io.github.matheus_fsantos.jp_users.utils.HateoasLinkBuilder;
import io.github.matheus_fsantos.jp_users.utils.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService<ResponseUserDTO, RequestUserDTO, UUID> {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final HateoasLinkBuilder hateoasLinkBuilder;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, HateoasLinkBuilder hateoasLinkBuilder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.hateoasLinkBuilder = hateoasLinkBuilder;
    }

    @Override
    public List<ResponseUserDTO> findAll() {
        return this.userRepository.findAll()
                .stream()
                .map(user -> {
                    ResponseUserDTO userDto = this.userMapper.toResponseUserDto(user);
                    return this.hateoasLinkBuilder.addSelfLink(userDto, user.getId());
                })
                .collect(Collectors.toList());
    }

    @Override
    public ResponseUserDTO findById(UUID expectedId) {
        User user = this.userRepository.findById(expectedId).orElseThrow(() -> new UserNotFoundException(expectedId));
        ResponseUserDTO dto = userMapper.toResponseUserDto(user);
        return hateoasLinkBuilder.addSelfLink(dto, user.getId());
    }

    @Override
    public void save(RequestUserDTO requestUserDto) {
        if(this.userRepository.findByEmail(requestUserDto.email()).isPresent())
            throw new UserAlreadyExistsException();

        User newUser = this.userMapper.toUser(requestUserDto);
        this.userRepository.save(newUser);
    }

    @Override
    public void update(RequestUserDTO requestUserDto, UUID id) {
        User existingUser = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        User userByEmail = this.userRepository.findByEmail(requestUserDto.email()).orElseThrow(() -> new UserNotFoundException(requestUserDto.email()));

        if(!userByEmail.getId().equals(id)) /* not allowed to update account, informed e-mail already in use to another account */
            throw new UserAlreadyExistsException();

        userMapper.updateUserFromDto(requestUserDto, existingUser);
        existingUser.setUpdatedAt(LocalDateTime.now());
        this.userRepository.save(existingUser);
    }

    @Override
    public void delete(UUID id) {
        if(this.userRepository.existsById(id))
            throw new UserNotFoundException(id);

        this.userRepository.deleteById(id);
    }
}
