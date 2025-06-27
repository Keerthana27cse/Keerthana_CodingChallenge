package com.example.codingchallenge.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.codingchallenge.model.Task;
import com.example.codingchallenge.service.ManagingTaskService;

@RestController
@RequestMapping("api/admin/tasks")
public class AdminTaskRestController
{
	@Autowired ManagingTaskService managingtaskservice;
	
	
	@PostMapping("/add")
	public ResponseEntity<Task> addTask(@RequestBody Task task)
	{
        Task newtask=managingtaskservice.addTask(task);
        return ResponseEntity.ok(newtask);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<String>updateTask(@RequestBody Task task,@PathVariable("id")Long id)
	{
		String result=managingtaskservice.updateTask(task, id);
		if(result.equals("InValid Id"))
		{
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String>delete(@PathVariable ("id")Long id)
	{
		try 
		{
		Task deleted=managingtaskservice.getTaskById(id);
        managingtaskservice.deleteTask(id);
	    return ResponseEntity.ok("Task Deleted");
		}
		catch(RuntimeException e)
		{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
		}
	}
}
