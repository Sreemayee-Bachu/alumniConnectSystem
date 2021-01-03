package com.sprint2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sprint2.entity.Alumni;
import com.sprint2.exceptions.ResourceNotFoundException;
import com.sprint2.repository.AlumniRepo;
import com.sprint2.service.AlumniService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class AlumniControl {
	@Autowired
	private AlumniService alumniService;
	
	@Autowired
	private AlumniRepo alumniRepo;
	
	@PostMapping("/alumni")
	@ApiOperation("Add Alumni Details")
	public Alumni createAlumniProfile(@RequestBody Alumni alumni) {
		return  alumniService.createAlumniProfile(alumni);
	}

	@GetMapping("/alumnis")
	@ApiOperation("View all alumni details")
	public List<Alumni> viewAlumni(){
		return alumniService.viewAlumni();
	}
	
//	
//	@RequestMapping(value = "/alumni/{id},{Choice},{newValue}", method = RequestMethod.PUT, produces = "application/json")
//	@ApiOperation("****Edit Alumni Details : 1.Stream  2.Email  3.Phone Number 4.Year Of Passing****")
//	public String updateAlumniDetails(@PathVariable("id") int id,@PathVariable("Choice")Integer choice,
//			@PathVariable("newValue")String newValue) throws ResourceNotFoundException{
//		int i=0;
//		int arr=0;
//		String[] res=new String[2];
//		Alumni aa=alumniRepo.findByAlumniID(id);
//		if(aa==null)
//		{
//			new ResourceNotFoundException("Alumni details not found for this id :: " +id);
//		}
//		else {
//			switch(choice) {
//			case 1: 
//				aa.setStream(newValue);
//				i=alumniService.updateAlumniDetails(aa);
//				res[0]="Name";res[1]=aa.getStream();
//				break;
//			case 2:
//				aa.setEmail(newValue);
//				i=alumniService.updateAlumniDetails(aa);
//				res[0]="Email";res[1]=aa.getEmail();
//				break;
//			case 3:
//				aa.setPhone(newValue);
//				i=alumniService.updateAlumniDetails(aa);
//				res[0]="Phone Number";res[1]=aa.getPhone();
//				break;
//			case 4:
//				int j=Integer.parseInt(newValue);
//				aa.setYearOfPassing(j);
//				i=alumniService.updateAlumniDetails(aa);
//				res[0]="Year Of Passing";
//				arr=aa.getYearOfPassing();
//				res[1]=String.valueOf(arr);
//			}
//		}
//		return "Your field "+res[0]+" is updated as "+res[1];
//	}
	
	@DeleteMapping("/alumni/{id}")
	@ApiOperation("Remove Alumni")
	public String deleteAlumni(@PathVariable("id") int id) throws ResourceNotFoundException {
		String result=alumniService.removeAlumni(id);
		if(result!=null)
			return "Profile Deleted";
		else
			return "Profile not found";
	}
	
	@GetMapping("/alumni/{AlumniId}")
	@ApiOperation("Find alumni details by ID")
	public Alumni getAlumniById(@PathVariable(value = "AlumniId") Integer id){
		Alumni aa=null;
		try {
			aa = alumniService.findUserById(id);
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return aa;
	}
	
	@RequestMapping(value = "/alumnis/{name}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation("Find alumni details by name")
	public Alumni getAlumniByName(@PathVariable(value="name") String aname){
		Alumni aa = null;
		try {
			aa = alumniService.findAlumniByname(aname);
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return aa;
	}
	
	@GetMapping("/alumnis/{stream}")
	@ApiOperation("Find alumni details by stream")
	public Alumni getAlumniByStream(@PathVariable(value="stream") String stream) {
		Alumni aa = null;
		try {
			aa = alumniService.findAlumniByStream(stream);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return aa;
	}
	
	@PutMapping("/alumni/{alumniId}")
	@ApiOperation("Update Alumni Details")
	public Alumni updateProfile(@PathVariable(value="alumniId") int id, @RequestBody Alumni alumni) {
		Alumni aa = null;
		try {
			 aa = alumniService.updateProfile(id, alumni);
			 
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return aa;
	}
	
	@GetMapping("/alumnis/{yop}")
	@ApiOperation("Find alumni details by yearOfPassing")
	public List<Alumni> getAlumniByYOP(@PathVariable(value="yop") int yop) throws ResourceNotFoundException{
		List<Alumni> aa=alumniService.findAlumniByYOP(yop);
		return  aa;
	}
	
	
}
