package com.fastcampus.ch4.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Cart {

	@Id
	@Column(name="cart_id")
	private Long id;
	@OneToOne
	@JoinColumn(name="member_id", nullable = false)
	private Member member;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", member=" + member + "]";
	}
	
	
}
