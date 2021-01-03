package com.sprint2.service;

import java.sql.SQLException;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;

import com.sprint2.entity.Alumni;
import com.sprint2.entity.Users;
import com.sprint2.exceptions.ResourceNotFoundException;
import com.sprint2.repository.AlumniRepo;
import com.sprint2.repository.UserRepo;

@Service
public class AlumniService implements IAlumniService{
	
	@Autowired
	private AlumniRepo alumniRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public Alumni createAlumniProfile(Alumni alumni) {
		return alumniRepo.save(alumni);
	}

	@Override
	public List<Alumni> viewAlumni() {	
		return alumniRepo.findAll();
	}

//	public int updateAlumniDetails(Alumni alumni) throws ResourceNotFoundException{
//		int i=0;
//		alumniRepo.save(alumni);
//		i=1;
//		return i;
//	}

	@Override
	public String removeAlumni(int id) throws ResourceNotFoundException {
		int aa=alumniRepo.deletedStatus("yes",id);
		if(aa!=0)
			return "Done";
		else
			return null;
	}

	@Override
	public Alumni findUserById(@PathVariable(value="id") Integer id){
		Alumni aa=alumniRepo.findByAlumniID(id);
		if(aa.equals(null)||aa.getDeleted().equals("yes")) {
			return null;
		}
		return aa;
	}

	@Override
	public Alumni findAlumniByname(@PathVariable(value="name") String aname){
		Alumni aa=alumniRepo.findByUserName(aname);
		if(aa.equals(null)||aa.getDeleted().equals("yes"))
		{
			return null;
		}
		return aa;
	}

	@Override
	public Alumni findAlumniByStream(@PathVariable(value="stream") String stream) {
		Alumni aa = alumniRepo.findByStream(stream);
			if(aa.equals(null)||aa.getDeleted().equals("yes"))
			{
				return null;
			}
			else
				return aa;
	}
	
	@ExceptionHandler({SQLException.class,DataAccessException.class})
	  public String databaseError() {
	    return "Not Found";
	  }

	@Override
	public Alumni updateProfile(@PathVariable(value="alumniId") int id, Alumni alumni) {
		Alumni aa = alumniRepo.findByAlumniID(id);
		aa.setAlumni_id(alumni.getAlumni_id());
		aa.setAlumniname(alumni.getAlumniname());
		aa.setEmail(alumni.getEmail());
		aa.setPhone(alumni.getPhone());
		aa.setDeleted(alumni.getDeleted());
		aa.setStream(alumni.getStream());
		aa.setYearOfPassing(alumni.getYearOfPassing());
		aa.setUser(alumni.getUser());
		aa.setWork(alumni.getWork());
		Alumni a1=alumniRepo.save(aa);
		if(a1.equals(null)||a1.getDeleted().equals("yes"))
		{
			return null;
		}
		else
			return a1;
	}

	public List<Alumni> findAlumniByYOP(@PathVariable(value="yop") int yop) {
		List<Alumni> aa=alumniRepo.findByYOP(yop);
		return aa;
	}

}
