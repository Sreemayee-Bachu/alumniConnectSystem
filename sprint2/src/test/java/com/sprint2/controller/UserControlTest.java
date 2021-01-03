package com.sprint2.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint2.Sprint2Application;
import com.sprint2.entity.Alumni;
import com.sprint2.entity.Users;
import com.sprint2.service.UserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Sprint2Application.class)
@WebMvcTest(value = UserController.class)
public class UserControlTest {
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private UserController userController;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCreateUser() throws Exception{
		String URI="/api/user";
		Users user= getUser();
		String jsonInput = this.converttoJson(user);
		Mockito.when(userController.createProfile(Mockito.any(Users.class))).thenReturn(user);
	    MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	    String jsonOutput = mockHttpServletResponse.getContentAsString();
	    assertThat(jsonInput).isEqualTo(jsonOutput);
		}
	
	@SuppressWarnings("deprecation")
	@Test
	public void viewAllUsers() throws Exception {
		String URI="/api/users";
		List<Users> list = new ArrayList<Users>();
		Users user= getUser();
		list.add(user);
		String jsonInput = this.converttoJson(list);
		Mockito.when(userController.viewUsers()).thenReturn(list);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	    String jsonOutput = mockHttpServletResponse.getContentAsString();
	    assertThat(jsonInput).isEqualTo(jsonOutput);
	}
	
	private Users getUser() {
		Users u=new Users();
		u.setUser_id(111);
		u.setUsername("abcd");
		u.setPassword("HeyABCD");
		u.setCreatedOn(Date.valueOf("2020-11-18"));
		return u;
	}
	
	private String converttoJson(Object admin) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(admin);
	}

}
