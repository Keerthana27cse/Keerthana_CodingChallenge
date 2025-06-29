package com.example.codingchallenge.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class User {
	
	@Id
	private String name;
	private String password;
	private String email;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(String name, String password, String email, Role role) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.role = role;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", email=" + email + ", role=" + role + "]";
	}
}
