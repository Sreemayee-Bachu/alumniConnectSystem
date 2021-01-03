package com.sprint2.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint2.entity.Alumni;
import com.sprint2.entity.Users;
import com.sprint2.repository.UserRepo;

@Service
public class UserService implements IUserService{
	@Autowired
	private UserRepo userRepo;

	@Override
	public Users createProfileService(Users users) {
		return userRepo.save(users);
		
	}

	public List<Users> viewUsers() {
		return userRepo.findAll();
	}
	
	
	
}
