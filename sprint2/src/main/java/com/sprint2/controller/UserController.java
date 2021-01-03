package com.sprint2.controller;

import java.util.List;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint2.entity.Users;
import com.sprint2.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired 
	private UserService userService;
	
	//Create user profile
	@PostMapping("/user")
	@ApiOperation("Create an user profile")
	public Users createProfile(@RequestBody Users u) {
		return userService.createProfileService(u);
		
	}
	
	//View all user profiles
	@GetMapping("/users")
	@ApiOperation("View all users")
	public List<Users> viewUsers(){
		return userService.viewUsers();
	}
	
	
}
