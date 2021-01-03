package com.sprint2.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint2.Sprint2Application;
import com.sprint2.entity.Alumni;
import com.sprint2.entity.Users;
import com.sprint2.entity.Work;
import com.sprint2.repository.AlumniRepo;
import com.sprint2.repository.UserRepo;
import com.sprint2.service.AlumniService;
import com.sprint2.service.UserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Sprint2Application.class)
@WebMvcTest(value = AlumniControl.class)
public class AlumniControlTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AlumniControl alumniControl;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void viewAllAlumniTest() throws Exception {
		String URI="/api/alumnis";
		List<Alumni> list = new ArrayList<Alumni>();
		Alumni alumni= getAlumni();
		list.add(alumni);
		String jsonInput = this.converttoJson(list);
		Mockito.when(alumniControl.viewAlumni()).thenReturn(list);
		 MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	    String jsonOutput = mockHttpServletResponse.getContentAsString();
	    assertThat(jsonInput).isEqualTo(jsonOutput);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void removeAlumniTest() throws Exception {
		String URI="/api/alumni/{id}";
		String str="Profile Deleted";
		Alumni alumni= getAlumni();
		String jsonInput = this.converttoJson(alumni);
		Mockito.when(alumniControl.deleteAlumni(Mockito.anyInt())).thenReturn(str);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI,111).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
		 MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		 String jsonOutput = mockHttpServletResponse.getContentAsString();
		 Assert.assertEquals(str, jsonOutput);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void viewAlumniByIDTest() throws Exception{
		String URI="/api/alumni/{AlumniId}";
		Alumni alumni= getAlumni();
		String jsonInput = this.converttoJson(alumni);
		Mockito.when(alumniControl.getAlumniById(Mockito.any())).thenReturn(alumni);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 111).accept(MediaType.APPLICATION_JSON)).andReturn();
	    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	    String jsonOutput = mockHttpServletResponse.getContentAsString();
	    assertThat(jsonInput).isEqualTo(jsonOutput);
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void addAlumniTest() throws Exception {
		String URI="/api/alumni";
		Alumni jsonInput= getAlumni();
		Mockito.when(alumniControl.createAlumniProfile(Mockito.any())).thenReturn(jsonInput);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
	    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		Alumni jsonOutput = alumniControl.createAlumniProfile(jsonInput);
		assertThat(jsonInput).isEqualTo(jsonOutput);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void viewAlumniByNameTest() throws Exception{
		String URI="/api/alumnis/{name}";
		Alumni alumni= getAlumni();
		String jsonInput = this.converttoJson(alumni);
		Mockito.when(alumniControl.getAlumniByName(Mockito.anyString())).thenReturn(alumni);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, "ABCD").accept(MediaType.APPLICATION_JSON)).andReturn();
	    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	    String jsonOutput = mockHttpServletResponse.getContentAsString();
	    assertThat(jsonInput).isEqualTo(jsonOutput);
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void updateAlumni() throws Exception {
		String URI = "/api/alumni/{alumniId}";
		Alumni alumni= getAlumni();
		String jsonInput = this.converttoJson(alumni);
		Mockito.when(alumniControl.updateProfile(Mockito.anyInt(), Mockito.any())).thenReturn(alumni);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI, 111).accept(MediaType.APPLICATION_JSON)).andReturn();
	    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	    Alumni jsonOutput = alumniControl.updateProfile(111, alumni);
	    assertThat(alumni).isEqualTo(jsonOutput);
	}
	
	private Alumni getAlumni() {
		Alumni alumni=new Alumni();
		alumni.setAlumni_id(111);
		alumni.setAlumniname("ABCD");
		alumni.setEmail("abcd123@gmail.com");
		alumni.setPhone("7894562213");
		alumni.setStream("ECE");
		alumni.setYearOfPassing(2020);
		alumni.setDeleted("no");
		return alumni;
	}
	
	private String converttoJson(Object a) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(a);
	}
}
