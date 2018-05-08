package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class DBServiceTestSuite {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() {
        //Given
        Task taskExample1 = new Task(1L, "a", "b");
        Task taskExample2 = new Task(2L, "c", "d");
        List<Task> tasksList = new ArrayList<>();
        tasksList.add(taskExample1);
        tasksList.add(taskExample2);

        when(taskRepository.findAll()).thenReturn(tasksList);

        //When
        List<Task> getTaskList = dbService.getAllTasks();

        //Then
        Assert.assertEquals(2, getTaskList.size());
    }

    @Test
    public void testGetTask() {
        //Given
        Task taskExample = new Task(1L, "a", "b");

        when(dbService.getTask(1L)).thenReturn(Optional.ofNullable(taskExample));

        //When
        Optional<Task> getTask = dbService.getTask(1L);

        //Then
        Assert.assertEquals(taskExample.getId(), getTask.get().getId());
    }

    @Test
    public void testSaveTask() {
        //Given
        Task taskExample = new Task (2L, "a", "b");
        when(taskRepository.save(taskExample)).thenReturn(taskExample);

        //When
        Task task = dbService.saveTask(taskExample);

        //Then
        Assert.assertEquals(2L, task.getId().longValue());
    }

    @Test
    public void testDeleteTask() {
        //Given
        Task taskExample = new Task (1L, "a", "b");

        List<Task> taskList = new ArrayList<>();
        taskList.add(taskExample);

        //When
        dbService.deleteTask(1L);

        //Then
        verify(taskRepository).delete(1L);
    }
}