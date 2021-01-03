package com.sprint2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint2.entity.Alumni;
import com.sprint2.entity.Posts;
import com.sprint2.repository.AlumniRepo;
import com.sprint2.repository.PostRepo;

@Service
public class PostService implements IPostService{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private AlumniRepo ar;
	
	@Override
	public Posts uploadPost(String alumniname, Posts posts) {
		Alumni aa=ar.findByUserName(alumniname);
		if(aa.getDeleted().equals("no")) {
			Posts p=new Posts();
			p.setPost_id(posts.getPost_id());
			p.setPost_content(posts.getPost_content());
			p.setPost_likes(posts.getPost_likes());
			p.setPost_dislikes(posts.getPost_dislikes());
			p.setPostedOn(posts.getPostedOn());
			p.setAlumni(aa);
			return postRepo.save(p);
		}
		return null;
	}

	public List<Posts> viewAllPosts() {
		return postRepo.findAll();
	}

	@Override
	public Posts searchByPostID(int pid) {
		return postRepo.findByPostID(pid);
	}

	@Override
	public String deletePostById(int id) {
		String str=null;
		postRepo.deleteById(id);
		str="Done";
		return str;
	}

}
