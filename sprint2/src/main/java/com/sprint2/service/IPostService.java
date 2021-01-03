package com.sprint2.service;

import java.util.List;

import com.sprint2.entity.Posts;

public interface IPostService {
	public Posts uploadPost(String alumniname, Posts posts);
	public List<Posts> viewAllPosts();
	public Posts searchByPostID(int pid);
	public String deletePostById(int id);
}
