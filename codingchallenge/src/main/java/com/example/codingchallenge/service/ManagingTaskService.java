package com.example.codingchallenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.codingchallenge.model.Task;
import com.example.codingchallenge.repo.ManagingTaskRepo;

@Service
public class ManagingTaskService {
	
	
	
	@Autowired ManagingTaskRepo managingtaskRepo;
	
	
	public List<Task> getAllTask()
	{
		return managingtaskRepo.findAll();	
	}
	
	public Task getTaskById(Long id)
	{
		return managingtaskRepo.findById(id).orElseThrow(()
				->new RuntimeException("Task not found with Id "+id));
	}
	
	public Task addTask(Task task)
	{
		return managingtaskRepo.save(task);
	}
	
	public String updateTask(Task task,Long id)
	{
		if(!managingtaskRepo.existsById(id))
		{
			return "InValid Id";
		}
		task.setId(id);
		managingtaskRepo.save(task);
		return "Task Updated Successfully";
	}
	
	public void deleteTask(Long id)
	{
		
	  managingtaskRepo.deleteById(id);
	}
}
		