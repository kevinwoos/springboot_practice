package com.fastcampus.ch4.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User2 {

	@Id
	@Column(name = "user_id")
	private String id;
	private String password;
	private String name;
	private String email;
	private Date inDate;	// 입력일
	private Date upDate;	// 변경일
	
//	@OneToMany(mappedBy = "user", fetch=FetchType.EAGER)
	@OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
	List<Board2> list = new ArrayList<>();
	
	
	public List<Board2> getList() {
		return list;
	}
	public void setList(List<Board2> list) {
		this.list = list;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	@Override
	public String toString() {
		return "User2 [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", inDate="
				+ inDate + ", upDate=" + upDate + ", list=" + list + "]";
	}
	

}
