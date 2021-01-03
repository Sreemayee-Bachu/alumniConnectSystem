package com.sprint2.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="comment_details")
public class Comments {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer comment_id;
	
	private String comment_content;
	private int comment_likes;
	private int comment_dislikes;
	
	@JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING )
	Date commentedOn;
	
	 @ManyToOne(optional = false)
	 @JoinColumn(name = "post_id")
	 @JsonIgnore
	 private Posts posts;

	public Comments(Integer comment_id, String comment_content, int comment_likes, int comment_dislikes,
			Date commentedOn, Posts posts) {
		super();
		this.comment_id = comment_id;
		this.comment_content = comment_content;
		this.comment_likes = comment_likes;
		this.comment_dislikes = comment_dislikes;
		this.commentedOn = commentedOn;
		this.posts=posts;
	}

	public Comments() {
		super();
	}

	public Integer getComment_id() {
		return comment_id;
	}

	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public int getComment_likes() {
		return comment_likes;
	}

	public void setComment_likes(int comment_likes) {
		this.comment_likes = comment_likes;
	}

	public int getComment_dislikes() {
		return comment_dislikes;
	}

	public void setComment_dislikes(int comment_dislikes) {
		this.comment_dislikes = comment_dislikes;
	}

	public Date getCommentedOn() {
		return commentedOn;
	}

	public void setCommentedOn(Date commentedOn) {
		this.commentedOn = commentedOn;
	}
	
	public Posts getPosts() {
		return posts;
	}

	public void setPosts(Posts posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Comments [comment_id=" + comment_id + ", comment_content=" + comment_content + ", comment_likes="
				+ comment_likes + ", comment_dislikes=" + comment_dislikes + ", commentedOn=" + commentedOn + "]";
	}
	 
	 
}
