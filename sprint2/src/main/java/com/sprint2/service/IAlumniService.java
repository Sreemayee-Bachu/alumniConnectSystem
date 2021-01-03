package com.sprint2.service;

import java.util.List;
import com.sprint2.entity.Alumni;
import com.sprint2.exceptions.ResourceNotFoundException;

public interface IAlumniService {
	public Alumni createAlumniProfile(Alumni alumni);
	public List<Alumni> viewAlumni();
//	int updateAlumniDetails(Alumni alumni) throws ResourceNotFoundException;
	String removeAlumni(int id) throws ResourceNotFoundException;
	public Alumni findUserById(Integer id);
	public Alumni findAlumniByname(String aname);
	public Alumni findAlumniByStream(String stream);
	public Alumni updateProfile(int id, Alumni alumni);
	public List<Alumni> findAlumniByYOP(int yop) ;
}
