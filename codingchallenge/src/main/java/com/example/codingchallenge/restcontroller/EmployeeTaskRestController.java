package com.example.codingchallenge.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.codingchallenge.model.Task;
import com.example.codingchallenge.service.ManagingTaskService;

@RestController
@RequestMapping("/api/user/tasks")
public class EmployeeTaskRestController 
{

	@Autowired ManagingTaskService  managingtaskservice;
	@GetMapping("/")
	public ResponseEntity<List<Task>> getAllTask()
	{
		return ResponseEntity.ok(managingtaskservice.getAllTask());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id)
	{
		try
		{
		Task task=managingtaskservice.getTaskById(id);
		return ResponseEntity.ok(task);
		}
		catch(RuntimeException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
