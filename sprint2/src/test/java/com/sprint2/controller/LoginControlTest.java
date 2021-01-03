package com.sprint2.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

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
import com.sprint2.entity.Users;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Sprint2Application.class)
@WebMvcTest(value = LoginControl.class)
public class LoginControlTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private LoginControl loginControl;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void loginTest() throws Exception{
		String URI="/api/login/{username}/{password}";
		Users user=new Users();
		user.setUser_id(111);
		user.setUsername("ABCDEF");
		user.setPassword("HeyHello");
		user.setUserType("Alumni");
		user.setCreatedOn(Date.valueOf("2020-09-09"));
		String jsonInput = this.converttoJson(user);
		Mockito.when(loginControl.loginByNamePassword(Mockito.anyString(), Mockito.anyString())).thenReturn("Login Successful");
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI,"ABCDEF","HeyHello").accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat("Login Successful").isEqualTo(jsonOutput);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void changePasswordTest() throws Exception{
		String URI="/api/loginUpdate/{password}/{username}";
		Users user=new Users();
		user.setUser_id(111);
		user.setUsername("ABCDEF");
		user.setPassword("HeyHello");
		user.setUserType("Alumni");
		user.setCreatedOn(Date.valueOf("2020-09-09"));
		String jsonInput = this.converttoJson(user);
		Mockito.when(loginControl.changePassword(Mockito.anyString(), Mockito.anyString())).thenReturn("Password changed");
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI,"HeyHello","ABCDEF").accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat("Password changed").isEqualTo(jsonOutput);
	}
	private String converttoJson(Object admin) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(admin);
	}
}
