package com.sprint2.service;

import java.util.List; 

import com.sprint2.entity.Comments;

public interface ICommentService {
	public Comments uploadComment(int id, Comments comment);
	public List<Comments> viewAllComments();
	public Comments searchByCommentID(int id);
	public String deleteCommentById(int id);
}
