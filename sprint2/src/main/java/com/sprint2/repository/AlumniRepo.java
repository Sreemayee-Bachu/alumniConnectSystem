package com.sprint2.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sprint2.entity.Users;
import com.sprint2.entity.Alumni;

@Repository
public interface AlumniRepo extends JpaRepository<Alumni, Integer>{
	@Query(value="select * from alumni_details where alumniname=?1",nativeQuery=true)
	public Alumni findByUserName(String uname);
	
	@Query(value="select * from alumni_details where alumni_id=?1",nativeQuery=true)
	 Alumni findByAlumniID(int aid);
	
	@Query(value="select * from alumni_details where stream=?1",nativeQuery=true)
	 Alumni findByStream(String uname);
	
	@Query(value="select * from alumni_details where year_of_passing=?1",nativeQuery=true)
	 List<Alumni> findByYOP(int aid);
	
	@Modifying
	@Transactional
	@Query(value="update alumni_details set deleted=?1 where alumni_id=?2",nativeQuery=true)
	int deletedStatus(String status,int id);
}
