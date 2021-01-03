package com.sprint2.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint2.Sprint2Application;
import com.sprint2.entity.Alumni;
import com.sprint2.entity.Posts;
import com.sprint2.entity.Users;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Sprint2Application.class)
@WebMvcTest(value = PostController.class)
public class PostControlTest {
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private PostController postControl;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void createPostTest() throws Exception{
		String URI="/api/alumni/id/posts/{name}";
		Posts post= getPost();
		String str="Post Uploaded";
		String jsonInput = this.converttoJson(post);
		Mockito.when(postControl.uploadPost(Mockito.any(),Mockito.any(Posts.class))).thenReturn(post);
	    MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI,"ABCD").accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	    String jsonOutput = mockHttpServletResponse.getContentAsString();
	    assertThat(jsonInput).isEqualTo(jsonOutput);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void viewAllUsersTest() throws Exception {
		String URI="/api/alumni/id/posts";
		List<Posts> list = new ArrayList<Posts>();
		Posts post= getPost();
		list.add(post);
		String jsonInput = this.converttoJson(list);
		Mockito.when(postControl.viewAllPosts()).thenReturn(list);
		 MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	    String jsonOutput = mockHttpServletResponse.getContentAsString();
	    assertThat(jsonInput).isEqualTo(jsonOutput);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void viewPostByIdTest() throws Exception {
		String URI="/api/alumni/id/posts/{Post_Id}";
		Posts post= getPost();
		String jsonInput = this.converttoJson(post);
		Mockito.when(postControl.searchPostById(Mockito.anyInt())).thenReturn(post);
	    MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI,111).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	    String jsonOutput = mockHttpServletResponse.getContentAsString();
	    assertThat(jsonInput).isEqualTo(jsonOutput);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void deletePost() throws Exception {
		String URI="/api/alumni/id/post/{PostId}";
		Posts post= getPost();
		String jsonInput = this.converttoJson(post);
		Mockito.when(postControl.deletePostById(Mockito.anyInt())).thenReturn("Done");
		 MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI,111).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	    String jsonOutput = mockHttpServletResponse.getContentAsString();
	    assertThat("Done").isEqualTo(jsonOutput);
	}
	
	private String converttoJson(Object admin) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(admin);
	}
	
	private Posts getPost() {
		Posts post=new Posts();
		Alumni alumni=new Alumni();
		alumni.setAlumniname("ABCD");
		alumni.setDeleted("no");
		if(alumni.getDeleted().equals("no")) {
			post.setPost_id(111);
			post.setPost_content("C:\\Users\\MyPc\\Pictures\\Screenshots\\1");
			post.setPost_likes(13);
			post.setPost_dislikes(12);
			post.setPostedOn(Date.valueOf("2020-09-07"));
		}
		return post;
	}
	
	
}
