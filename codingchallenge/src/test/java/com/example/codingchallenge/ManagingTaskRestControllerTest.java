package com.example.codingchallenge;

import com.example.codingchallenge.model.Priority;
import com.example.codingchallenge.model.Task;
import com.example.codingchallenge.model.TaskStatus;
import com.example.codingchallenge.restcontroller.AdminTaskRestController;
import com.example.codingchallenge.service.ManagingTaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminTaskRestController.class)
public class ManagingTaskRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ManagingTaskService managingTaskService;

    private Task sampleTask;

    @BeforeEach
    public void setup() {
        sampleTask=new Task();
        sampleTask.setId(1L);
        sampleTask.setTitle("Test Task");
        sampleTask.setDescription("This is a test");
        sampleTask.setDueDate(LocalDate.of(2025, 7, 1));
        sampleTask.setPriority(Priority.HIGH);
        sampleTask.setTaskStatus(TaskStatus.PENDING);
    }
    
    


    @Test
    public void testAddTask()throws Exception {
        when(managingTaskService.addTask(any(Task.class))).thenReturn(sampleTask);

        mockMvc.perform(post("/api/admin/tasks/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(sampleTask)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Task"));
    }

    @Test
    public void testUpdateTask_Success()throws Exception {
        when(managingTaskService.updateTask(any(Task.class), eq(1L))).thenReturn("Task Updated Successfully");

        mockMvc.perform(put("/api/admin/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(sampleTask)))
                .andExpect(status().isOk())
                .andExpect(content().string("Task Updated Successfully"));
    }


    @Test
    public void testUpdateTask_InvalidId()throws Exception {
        when(managingTaskService.updateTask(any(Task.class), eq(2L))).thenReturn("InValid Id");

        mockMvc.perform(put("/api/admin/tasks/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(sampleTask)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("InValid Id"));
    }

    @Test
    public void testDeleteTask_Success()throws Exception {
        when(managingTaskService.getTaskById(1L)).thenReturn(sampleTask);
        doNothing().when(managingTaskService).deleteTask(1L);

        mockMvc.perform(delete("/api/admin/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Task Deleted"));
    }

    @Test
    public void testDeleteTask_NotFound()throws Exception {
        when(managingTaskService.getTaskById(2L)).thenThrow(new RuntimeException("Task not found"));
        mockMvc.perform(delete("/api/admin/tasks/2"))
        .andExpect(status().isNotFound());
    }

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
