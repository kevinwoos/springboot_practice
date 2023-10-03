package com.fastcampus.ch4.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Board2 {
	@Id 
//	@GeneratedValue
	private Long bno;
	private String title;
//	private String writer;
	private String content;
	private Long viewCnt;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date inDate;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date upDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User2 user;

	public Long getBno() {
		return bno;
	}

	public void setBno(Long bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(Long viewCnt) {
		this.viewCnt = viewCnt;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Date getUpDate() {
		return upDate;
	}

	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}

	public User2 getUser() {
		return user;
	}

	public void setUser(User2 user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Board2 [bno=" + bno + ", title=" + title + ", content=" + content + ", viewCnt=" + viewCnt + ", inDate="
				+ inDate + ", upDate=" + upDate 
//				+ ", user=" + user 
				+ "]";
	}
	
	
}
