package io.github.matheus_fsantos.jp_users.controller;

import io.github.matheus_fsantos.jp_users.dto.RequestUserDTO;
import io.github.matheus_fsantos.jp_users.dto.ResponseUserDTO;
import io.github.matheus_fsantos.jp_users.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/users", produces = { MediaType.APPLICATION_JSON_VALUE })
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    /*
    *
    * !!! IMPORTANT NOTE !!!
    *
    * >> In the future, add Spring HATEOAS to this controller.
    * >> Add swagger to this controller.
    *
    * */

    @GetMapping
    public ResponseEntity<List<ResponseUserDTO>> findAll() {
        return ResponseEntity.status(200).body(this.userService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseUserDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(this.userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody RequestUserDTO requestUserDto) {
        this.userService.save(requestUserDto);
        return ResponseEntity.status(201).build();
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody RequestUserDTO requestUserDto, @PathVariable UUID id) {
        this.userService.update(requestUserDto, id);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.userService.delete(id);
        return ResponseEntity.status(204).build();
    }

}
