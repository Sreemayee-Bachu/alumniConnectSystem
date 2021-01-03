package com.sprint2.service;
import java.util.List;

import com.sprint2.entity.Users;

public interface IUserService {
	Users createProfileService(Users users);
	public List<Users> viewUsers();
}
