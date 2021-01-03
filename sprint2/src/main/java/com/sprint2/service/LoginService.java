package com.sprint2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sprint2.entity.Users;
import com.sprint2.exceptions.ResourceNotFoundException;
import com.sprint2.repository.LoginRepo;
import com.sprint2.repository.UserRepo;

@Service
public class LoginService implements ILoginService{
	
	@Autowired
	private LoginRepo loginRepo;
	
	@Override
	public String login(@PathVariable(value = "username") String username,@PathVariable(value = "password") String password) throws ResourceNotFoundException{
		String str=null;
		Users user=loginRepo.loginByNamePassword(username, password);
		if(user!=null)
			return "Done";
		else
			return str;
	}

	@Override
	public String changePassword(@PathVariable(value = "password") String password,@PathVariable(value = "username") String username) throws ResourceNotFoundException{
		String str=null;
		int i=loginRepo.changePassword(password, username);
		if(i!=0)
			return "Done";
		else
			return str;
	}

	
}
