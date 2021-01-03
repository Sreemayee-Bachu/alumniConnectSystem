package com.sprint2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint2.entity.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{
	@Query(value="select * from user_details where username=?1",nativeQuery=true)
	public Users findByUserName(String uname);
	
	@Query(value="select * from user_details where user_id=?1",nativeQuery=true)
	public Users findByUserID(int id);
}
