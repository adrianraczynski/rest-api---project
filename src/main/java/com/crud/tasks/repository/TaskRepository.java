package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();

    Task findTaskById(Long id);

    //Optional<Task> findTaskById(Long id);

    @Override
    Task save (Task task);

    //@Override
    //Task delete (Task task);

}
