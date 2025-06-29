package com.example.codingchallenge.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.codingchallenge.model.User;
import com.example.codingchallenge.repo.UserRepo;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired UserRepo userRepo;
	
	
	@PostMapping("/")
	public ResponseEntity<User> savedUser(@RequestBody User user)
	{

		User saved=userRepo.save(user);
		return ResponseEntity.ok(saved);
	}
	
	@GetMapping ("/{username}")
	public ResponseEntity<?>getUser(@PathVariable("username") String name)
	{
	User user=userRepo.getReferenceById(name);
	return ResponseEntity.ok(user);
	}
}
		
