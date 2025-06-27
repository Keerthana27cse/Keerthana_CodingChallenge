package com.example.codingchallenge.restcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class task {
	@GetMapping("/")
	public String show()
	{
		return "Welcome Keerthana";
	}
}
