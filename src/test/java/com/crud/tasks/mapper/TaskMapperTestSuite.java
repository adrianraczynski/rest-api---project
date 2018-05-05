package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class TaskMapperTestSuite {

    private TaskMapper taskMapper = new TaskMapper();

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test1","test content");

        //When
        Task exampleTask = taskMapper.mapToTask(taskDto);

        //Then
        Assert.assertEquals(1L, exampleTask.getId().longValue());
        Assert.assertEquals("test1", exampleTask.getTitle());
        Assert.assertEquals("test content", exampleTask.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task (2L, "test2", "test content");

        //When
        TaskDto exampleTask = taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertEquals(2L, exampleTask.getId().longValue());
        Assert.assertEquals("test2", exampleTask.getTitle());
        Assert.assertEquals("test content", exampleTask.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task = new Task (2L, "test2", "test content");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);

        //When
        List<TaskDto> exampleTaskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        Assert.assertEquals(1, exampleTaskDtoList.size());
    }
}
