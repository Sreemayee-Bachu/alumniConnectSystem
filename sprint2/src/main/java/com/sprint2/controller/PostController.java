package com.sprint2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint2.entity.Posts;
import com.sprint2.service.PostService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
	private PostService postService;
	
	@PostMapping("/alumni/id/posts/{name}")
	@ApiOperation("Upload Post")
	public Posts uploadPost(@PathVariable("name") String uname, @RequestBody Posts p) {
		return postService.uploadPost(uname, p);
	}
	
	@GetMapping("/alumni/id/posts")
	@ApiOperation("View all posts")
	public List<Posts> viewAllPosts(){
		return postService.viewAllPosts();
	}
	
	@GetMapping("/alumni/id/posts/{Post_Id}")
	@ApiOperation("View post by Id")
	public Posts searchPostById(@PathVariable (value="Post_Id") int id) {
		return postService.searchByPostID(id);	
	}
	
	@DeleteMapping("/alumni/id/post/{PostId}")
	@ApiOperation("Delete post by Id")
	public String deletePostById(@PathVariable (value="PostId") int id) {
		String result = postService.deletePostById(id);
		if(result!=null)
			return "Post Deleted";
		else
			return "Post not Deleted";
	}
}
