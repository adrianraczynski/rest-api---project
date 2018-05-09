package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService service;


    @Test
    public void testGetEmptyTaskList() throws Exception {
        //Given
        //List<Task> taskList = new ArrayList<>();
        //List<TaskDto> taskDtoList = new ArrayList<>();
        //when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))    // or isOk()
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testGetTasksList() throws Exception {     // metoda List<TaskDto> getTasks()z TaskController
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "testTask1", "contentTask1"));
        taskList.add(new Task(2L, "testTask2", "contentTask2"));

        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "testTaskDto1", "contentTaskDto1"));
        taskDtoList.add(new TaskDto(2L, "testTaskDto2", "contentTaskDto2"));

        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //TaskList 1 [0] and 2 [1]
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[0].title", is("testTaskDto1")))
                .andExpect(jsonPath("$[1].title", is("testTaskDto2")))
                .andExpect(jsonPath("$[0].content", is("contentTaskDto1")))
                .andExpect(jsonPath("$[1].content", is("contentTaskDto2")));
    }

    @Test
    public void testGetTasks() throws Exception {
        //Given
        Task task = new Task(1L, "testTask1", "contentTask1");
        Optional<Task> optionalTask = Optional.of(task);
        TaskDto taskDto = new TaskDto(1L, "test taskDTOAAAA", "content taskDTOBBBB");

        when(service.getTask(task.getId())).thenReturn(optionalTask);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //Then and When
        mockMvc.perform(get("/v1/task/getTask?taskId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test taskDTOAAAA")))
                .andExpect(jsonPath("$.content", is("content taskDTOBBBB")));

    }

    @Test
    public void testDeleteTask() throws Exception {
        //Given

        //When and Then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service).deleteTask(1L);
    }

    @Test
    public void testUpdateTask() throws Exception {
        //Given
        Task task = new Task(1L, "testTask1", "contentTask1");
        TaskDto taskDto = new TaskDto(1L, "testTask1", "contentTask1");

        when(taskMapper.mapToTask(Mockito.any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When and Then
        mockMvc.perform(put("/v1/task/updateTask?taskDtoId=1") //ma być taki adres jak w ""?
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("testTask1")))
                .andExpect(jsonPath("$.content", is("contentTask1")));
    }

    @Test
    public void testCreateTask() throws Exception {     //CZY TO JEST DOBRZE??
        //Given
        Task task = new Task(1L, "testTask1", "contentTask1");
        TaskDto taskDto = new TaskDto(1L, "testTask1", "contentTask1");

        when(taskMapper.mapToTask(Mockito.any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When and Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
        verify(service).saveTask(taskMapper.mapToTask(taskDto));
    }
}