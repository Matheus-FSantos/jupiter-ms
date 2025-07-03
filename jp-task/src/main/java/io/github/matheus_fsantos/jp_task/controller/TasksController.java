package io.github.matheus_fsantos.jp_task.controller;

import io.github.matheus_fsantos.jp_task.dto.RequestTaskDTO;
import io.github.matheus_fsantos.jp_task.model.Task;
import io.github.matheus_fsantos.jp_task.service.impl.TaskServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/tasks", produces = { MediaType.APPLICATION_JSON_VALUE })
public class TasksController {
    @Autowired
    private TaskServiceImpl taskServiceImpl;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.status(200).body(this.taskServiceImpl.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Task> getTask(UUID id) {
        return ResponseEntity.status(200).body(this.taskServiceImpl.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody RequestTaskDTO requestTaskDTO) {
        this.taskServiceImpl.save(requestTaskDTO);
        return ResponseEntity.status(201).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody RequestTaskDTO requestTaskDTO, UUID uuid) {
        this.taskServiceImpl.update(requestTaskDTO, uuid);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(UUID uuid) {
        this.taskServiceImpl.delete(uuid);
        return ResponseEntity.status(204).build();
    }

}
