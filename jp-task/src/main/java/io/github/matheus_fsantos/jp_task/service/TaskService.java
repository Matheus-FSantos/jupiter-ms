package io.github.matheus_fsantos.jp_task.service;

import java.util.List;

public interface TaskService<T, DTO, ID> {
    List<T> findAll();
    T findById(ID id);
    void save(DTO dto);
    void update(DTO dto, ID id);
    void delete(ID id);
}
