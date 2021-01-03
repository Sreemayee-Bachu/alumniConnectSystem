package com.sprint2.entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="post_details")
public class Posts {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer post_id;
	
	private String post_content;
	private int post_likes;
	private int post_dislikes;
	
	@JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING )
	Date postedOn;
	
	 @ManyToOne(optional = false)
	 @JoinColumn(name="alumni_id")
	 @JsonIgnore
	 private Alumni alumni;

	 public Posts(Integer post_id, String post_content, int post_likes, int post_dislikes,
			Date postedOn) {
		super();
		this.post_id = post_id;
		this.post_content = post_content;
		this.post_likes = post_likes;
		this.post_dislikes = post_dislikes;
		this.postedOn = postedOn;
	}

	public Posts() {
		super();
	}

	public Integer getPost_id() {
		return post_id;
	}

	public void setPost_id(Integer post_id) {
		this.post_id = post_id;
	}

	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}

	public int getPost_likes() {
		return post_likes;
	}

	public void setPost_likes(int post_likes) {
		this.post_likes = post_likes;
	}

	public int getPost_dislikes() {
		return post_dislikes;
	}

	public void setPost_dislikes(int post_dislikes) {
		this.post_dislikes = post_dislikes;
	}

	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	public Alumni getAlumni() {
		return alumni;
	}

	public void setAlumni(Alumni alumni) {
		this.alumni = alumni;
	}

	@Override
	public String toString() {
		return "Posts [post_id=" + post_id + ", post_content=" + post_content + ", post_likes=" + post_likes
				+ ", post_dislikes=" + post_dislikes + ", postedOn=" + postedOn + ", alumni=" + alumni + "]";
	}

	
	 
	 
}
