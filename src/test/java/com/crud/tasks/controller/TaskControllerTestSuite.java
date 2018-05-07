package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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
        List<Task> taskList = new ArrayList<>();
        List<TaskDto> taskDtoList = new ArrayList<>();
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))    // or isOk()
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testGetTasksList() throws Exception {     // metoda List<TaskDto> getTasks()z TaskController
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "testTask1","contentTask1"));
        taskList.add(new Task(2L, "testTask2","contentTask2"));

        List<TaskDto> taskDtoList = new ArrayList<>();

        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //TaskList 1 [0] and 2 [1]
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1L)))
                .andExpect(jsonPath("$[1].id", is(2L)))
                .andExpect(jsonPath("$[0].title", is("testTask1")))
                .andExpect(jsonPath("$[1].title", is("testTask2")))
                .andExpect(jsonPath("$[0].content", is("contentTask1")))
                .andExpect(jsonPath("$[1].content", is("contentTask2")));
    }

    @Test
    public void testGetTasks() {
        //Given


        //When


        //Then

    }

    @Test
    public void testDeleteTask() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "testTask1","contentTask1"));
        taskList.add(new Task(2L, "testTask2","contentTask2"));

        when(service.deleteTask(1L)).thenReturn(taskList);

        //When and Then
        mockMvc.perform(delete("/v1/task/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(2L)));
    }

    @Test
    public void testUpdateTask() {
        //Given
        Task task = new Task(1L, "testTask1","contentTask1");

        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When and Then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1L)))
                .andExpect(jsonPath("$.title", is("testTask1")))
                .andExpect(jsonPath("$.content", is("contentTask1")));
    }

    @Test
    public void testCreateTask() {
        //Given
        Task task = new Task(1L, "testTask1","contentTask1");

        when(service.saveTask(any(Task.class))).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When and Then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1L)))
                .andExpect(jsonPath("$.title", is("testTask1")))
                .andExpect(jsonPath("$.content", is("contentTask1")));
    }
}
