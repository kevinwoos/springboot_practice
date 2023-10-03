package com.fastcampus.ch4.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fastcampus.ch4.entity.Board;
import com.fastcampus.ch4.entity.Cart;
import com.fastcampus.ch4.entity.Member;
import com.fastcampus.ch4.entity.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.fastcampus.ch4.entity.QBoard.board;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardRepositoryTest5 {

	@Autowired
	public EntityManager em;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	
	@Test
	@DisplayName("1:1 조인 쿼리 작성 테스트")
	public void oneToOneTest() {
		
		Member member = new Member();
		member.setId(1L);
		member.setName("aaa");
		member.setEmail("aaa@aaa.com");
		member.setPassword("1234");
		
		memberRepository.save(member);
		
		Cart cart = new Cart();
		cart.setId(1L);
		cart.setMember(member);
		cartRepository.save(cart);
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
		
		Cart cart2 = cartRepository.findById(cart.getId()).orElse(null);
		
		System.out.println("----------------- cart2 = " + cart2);
		assertTrue(cart2 != null);
		
		member = memberRepository.findById(member.getId()).orElse(null);
		
		System.out.println("================== member = " + member);
		assertTrue(member != null);
	}


}
