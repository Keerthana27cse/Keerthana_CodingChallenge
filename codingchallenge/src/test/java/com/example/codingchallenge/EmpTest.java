package com.example.codingchallenge;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.codingchallenge.model.Priority;
import com.example.codingchallenge.model.Task;
import com.example.codingchallenge.model.TaskStatus;
import com.example.codingchallenge.restcontroller.EmployeeTaskRestController;
import com.example.codingchallenge.service.ManagingTaskService;

@WebMvcTest(EmployeeTaskRestController.class)
public class EmpTest {
	
	@Autowired private MockMvc mockMvc;
	
	@MockBean
	private ManagingTaskService  managingTaskService;
	
	private Task sampleTask;
	
	@BeforeEach
	public void setup()
	{
		
		sampleTask =new Task();
		        sampleTask.setId(1L);
		        sampleTask.setTitle("Test Task");
		        sampleTask.setDescription("This is a test");
		        sampleTask.setDueDate(LocalDate.of(2025, 7, 1));
		        sampleTask.setPriority(Priority.HIGH);
		        sampleTask.setTaskStatus(TaskStatus.PENDING);
	 }
	

    @Test
    public void testGetAllTasks() throws Exception {
        List<Task> tasks=Arrays.asList(sampleTask);
        when(managingTaskService.getAllTask()).thenReturn(tasks);

        mockMvc.perform(get("/api/user/tasks")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Task"));
    }

    @Test
    public void testGetTaskById()throws Exception {
        when(managingTaskService.getTaskById(1L)).thenReturn(sampleTask);

        mockMvc.perform(get("/api/user/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("This is a test"));
    }
}