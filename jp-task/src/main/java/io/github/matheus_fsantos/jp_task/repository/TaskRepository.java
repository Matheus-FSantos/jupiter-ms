package io.github.matheus_fsantos.jp_task.repository;

import io.github.matheus_fsantos.jp_task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByOwnerId(UUID ownerId);
}
