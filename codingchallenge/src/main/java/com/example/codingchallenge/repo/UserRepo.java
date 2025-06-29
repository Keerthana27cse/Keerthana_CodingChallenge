package com.example.codingchallenge.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.codingchallenge.model.User;

public interface UserRepo extends JpaRepository<User,String> 
{


}
