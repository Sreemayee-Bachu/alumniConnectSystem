package com.sprint2.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="USER_DETAILS")
public class Users {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer user_id;
	
	@Column(unique = true)
	@NotEmpty(message = "User name is required!!!")
	@Pattern(regexp = "^[a-zA-Z0-9]{3,}$")
	private String username;
	
	@NotEmpty(message="Password can't be empty!!!")
	private String password;
	
	@JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING )
	Date createdOn;
	
	private String userType;
	
	public Users(Integer user_id,
			@NotEmpty(message = "User name is required!!!") @Pattern(regexp = "^[a-zA-Z]{3,}$") String username,
			@NotEmpty(message="Password can't be empty!!!") String password,
			Date createdOn, String userType) {
		super();
		this.user_id=user_id;
		this.username=username;
		this.password=password;
		this.createdOn=createdOn;
		this.userType=userType;
	}
	
	public Users() {
		super();
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", username=" + username + ", password=" + password + ", createdOn="
				+ createdOn + ", userType=" + userType + "]";
	}

	
	
	
}
