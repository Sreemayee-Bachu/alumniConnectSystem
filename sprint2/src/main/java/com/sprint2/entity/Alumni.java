package com.sprint2.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * @author MyPc
 *
 */
@Entity
@Table(name="alumni_details")
public class Alumni {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer alumni_id;
	
	@Column(unique = true)
	@Pattern(regexp = "^[a-zA-Z]{3,}$")
	private String alumniname;
	
	private String stream;
	private int yearOfPassing;
	
	@Pattern(regexp = "^[6-9]{1}[0-9]{9}$", message = "Mobile number is invalid")
	private String phone;
	
	@Column(unique = true)
	@Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9]{2,}@[a-z]{5}.com$", message = "Email address is invalid")
	private String email;
	
	@NotNull
	private String deleted;
	
	@OneToOne
	private Users user;
	
	@Embedded
	Work work;

	public Alumni(Integer alumni_id,
			@Pattern(regexp = "^[a-zA-Z]{3,}$") String alumniname,
			String stream, int yearOfPassing,
			@Pattern(regexp = "^[6-9]{1}[0-9]{9}$", message = "Mobile number is invalid") String phone,
			@Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9]{2,}@[a-z]{5}.com$", message = "Email address is invalid") String email,String deleted,Users user,Work work) {
		super();
		this.alumni_id = alumni_id;
		this.alumniname = alumniname;
		this.stream = stream;
		this.yearOfPassing = yearOfPassing;
		this.phone = phone;
		this.email = email;
		this.deleted=deleted;
		this.user=user;
		this.work=work;
	}
	
	public Alumni() {
		super();
	}

	public Integer getAlumni_id() {
		return alumni_id;
	}

	public void setAlumni_id(Integer alumni_id) {
		this.alumni_id = alumni_id;
	}

	public String getAlumniname() {
		return alumniname;
	}

	public void setAlumniname(String alumniname) {
		this.alumniname = alumniname;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public int getYearOfPassing() {
		return yearOfPassing;
	}

	public void setYearOfPassing(int yearOfPassing) {
		this.yearOfPassing = yearOfPassing;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	
	@Override
	public String toString() {
		return "Alumni [alumni_id=" + alumni_id + ", alumniname=" + alumniname + ", stream=" + stream
				+ ", yearOfPassing=" + yearOfPassing + ", phone=" + phone + ", email=" + email + ", deleted=" + deleted
				+ ", work=" + work + "]";
	}	
	
	
}
