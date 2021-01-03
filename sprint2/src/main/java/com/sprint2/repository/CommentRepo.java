package com.sprint2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint2.entity.Comments;

@Repository
public interface CommentRepo extends JpaRepository<Comments, Integer>{
	@Query(value="select * from comment_details where comment_id=?1",nativeQuery=true)
	 Comments findByCommentID(int id);
}
