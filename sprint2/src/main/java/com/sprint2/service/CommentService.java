package com.sprint2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sprint2.entity.Comments;
import com.sprint2.entity.Posts;
import com.sprint2.repository.CommentRepo;
import com.sprint2.repository.PostRepo;

@Service
public class CommentService implements ICommentService{
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo pr;
	
	@Override
	public Comments uploadComment(@PathVariable(value="id") int id , Comments comment) {
		Posts p=pr.findByPostID(id);
		Comments c=new Comments();
		c.setComment_id(comment.getComment_id());
		c.setComment_content(comment.getComment_content());
		c.setComment_likes(comment.getComment_likes());
		c.setComment_dislikes(comment.getComment_dislikes());
		c.setCommentedOn(comment.getCommentedOn());
		c.setPosts(p);
		return commentRepo.save(c);
	}

	@Override
	public List<Comments> viewAllComments() {
		return commentRepo.findAll();
	}

	@Override
	public Comments searchByCommentID(int id) {
		return commentRepo.findByCommentID(id);
	}

	@Override
	public String deleteCommentById(int id) {
		String str=null;
		commentRepo.deleteById(id);
		str="Done";
		return str;
	}

}
