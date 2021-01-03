package com.sprint2.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint2.entity.Comments;
import com.sprint2.exceptions.ResourceNotFoundException;
import com.sprint2.service.CommentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	private static final Logger logger =  LoggerFactory.getLogger(ResourceNotFoundException.class);
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CommentService commentService;
	
	
	@PostMapping("/alumnis/alumniId/posts/postId/comment/{id}")
	@ApiOperation("Upload a comment")
	public Comments createComment(@PathVariable(value="id") int id,@RequestBody Comments comment) {
		return commentService.uploadComment(id, comment);
	}
	
	@GetMapping("/alumnis/alumniId/posts/postId/comment/commentId")
	@ApiOperation("View all comments")
	public List<Comments> viewAllComments(){
		return commentService.viewAllComments();
	}

	@GetMapping("/alumnis/alumniId/posts/postId/comment/{comment_id}")
	@ApiOperation("View comment by id")
	public Comments searchByCommentID(@PathVariable(value="comment_id") int id) {
		return commentService.searchByCommentID(id);
	}
	
	@DeleteMapping("/alumnis/alumniId/posts/postId/comment/{c_id}")
	@ApiOperation("Delete Comment by Id")
	public String deleteCommentById(@PathVariable (value="c_id") int id) {
		String result = commentService.deleteCommentById(id);
		if(result!=null)
			return "Comment Deleted";
		else
			return "Comment not Deleted";
	}
}
