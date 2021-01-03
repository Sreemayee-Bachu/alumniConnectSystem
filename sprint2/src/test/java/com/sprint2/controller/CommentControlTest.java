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
import com.sprint2.entity.Comments;
import com.sprint2.entity.Posts;
import com.sprint2.entity.Comments;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Sprint2Application.class)
@WebMvcTest(value = CommentController.class)
public class CommentControlTest {
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private CommentController commentControl;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void viewCommentByIdTest() throws Exception {
		String URI="/api/alumnis/alumniId/posts/postId/comment/{comment_id}";
		Comments comment = getComment();
		String jsonInput = this.converttoJson(comment);
		Mockito.when(commentControl.searchByCommentID(Mockito.anyInt())).thenReturn(comment);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI,111).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat(jsonInput).isEqualTo(jsonOutput);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void viewAllCommentsTest() throws Exception{
		String URI="/api/alumnis/alumniId/posts/postId/comment/commentId";
		List<Comments> list= new ArrayList<Comments>();
		Comments comments = getComment();
		list.add(comments);
		String jsonInput = this.converttoJson(list);
		Mockito.when(commentControl.viewAllComments()).thenReturn(list);
		 MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	    String jsonOutput = mockHttpServletResponse.getContentAsString();
	    assertThat(jsonInput).isEqualTo(jsonOutput);
			
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void deleteComment() throws Exception {
		String URI="/api/alumnis/alumniId/posts/postId/comment/{c_id}";
		Comments comment = getComment();
		String jsonInput = this.converttoJson(comment);
		Mockito.when(commentControl.deleteCommentById(Mockito.anyInt())).thenReturn("Comment Deleted");
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI,111).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat("Comment Deleted").isEqualTo(jsonOutput);
	}
	private Comments getComment() {
		Comments comment=new Comments();
		comment.setComment_id(111);
		comment.setComment_content("Hello Hi");
		comment.setComment_likes(12);
		comment.setComment_dislikes(2);
		comment.setCommentedOn(Date.valueOf("2020-09-09"));
		return comment;
	}
	
	private String converttoJson(Object admin) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(admin);
	}
}
