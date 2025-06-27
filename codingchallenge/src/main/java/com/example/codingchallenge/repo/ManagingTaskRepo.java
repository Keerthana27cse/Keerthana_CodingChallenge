package com.example.codingchallenge.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.codingchallenge.model.Task;

public interface ManagingTaskRepo extends JpaRepository<Task,Long>
{
	
}
