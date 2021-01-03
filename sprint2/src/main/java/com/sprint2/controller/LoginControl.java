package com.sprint2.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sprint2.entity.Users;
import com.sprint2.exceptions.ResourceNotFoundException;
import com.sprint2.service.LoginService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class LoginControl {
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/login/{username}/{password}")
	@ApiOperation(value="Login by username and password")
	public String loginByNamePassword(@PathVariable(value = "username") String username,@PathVariable(value = "password") String password) throws ResourceNotFoundException{
		String result= loginService.login(username, password);
		if(result!=null)
			return "Login Successful";
		else
			return "Failed!!";
	}
	
	@PutMapping("/loginUpdate/{password}/{username}")
	@ApiOperation("Change Password")
	public String changePassword(@PathVariable(value = "password") String password,@PathVariable(value = "username") String username) throws ResourceNotFoundException{
		String result=loginService.changePassword(password, username);
		if(result!=null)
			return "Password changed";
		else
			return "Failed!!";
	}
}
