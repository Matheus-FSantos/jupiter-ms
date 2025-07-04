package io.github.matheus_fsantos.jp_task.service;

import java.util.List;

public interface TaskService<T, PostDTO, PutDTO, ID> {
    List<T> findAll();
    T findById(ID id);
    List<T> findByOwnerId(ID id);
    void save(PostDTO dto);
    void update(PutDTO dto, ID id);
    void delete(ID id);
}
