package com.sprint2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint2.entity.Alumni;
import com.sprint2.entity.Posts;

@Repository
public interface PostRepo extends JpaRepository<Posts, Integer>{
	@Query(value="select * from post_details where post_id=?1",nativeQuery=true)
	 Posts findByPostID(int pid);
}
