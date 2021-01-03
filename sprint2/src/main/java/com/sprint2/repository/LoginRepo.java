package com.sprint2.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sprint2.entity.Users;

@Repository
public interface LoginRepo extends JpaRepository<Users, Integer>{
	@Transactional
	@Query(value="select * from USER_DETAILS where username=?1 AND password=?2",nativeQuery=true)
	 public Users loginByNamePassword(String uname, String pass);
	
	@Modifying
	@Transactional
	@Query(value="update USER_DETAILS set password=?1 where username=?2",nativeQuery=true)
	 int changePassword(String password, String username);
}
