package com.fastcampus.ch4.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fastcampus.ch4.entity.Board;
import com.fastcampus.ch4.entity.Board2;
import com.fastcampus.ch4.entity.Cart;
import com.fastcampus.ch4.entity.Member;
import com.fastcampus.ch4.entity.QBoard;
import com.fastcampus.ch4.entity.User2;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.fastcampus.ch4.entity.QBoard.board;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OneToManyTest {

	@Autowired
	public EntityManager em;
	
	@Autowired
	private BoardRepository2 boardRepository;
	
	@Autowired
	private UserRepository2 userRepository;
	
	@Transactional
	@Test
	@DisplayName("ManyToOne 조인 쿼리 작성 테스트")
	public void manyToOneTest() {
		
		
        // 1. 테스트 데이터 작성
        User2 user = new User2();
        user.setId("aaa");
        user.setPassword("1234");
        user.setName("LEE");
        user.setEmail("aaa@aaa.com");
        user.setInDate(new Date());
        user.setUpDate(new Date());
        userRepository.save(user);

        Board2 b1 = new Board2();
        b1.setBno(1L);
        b1.setTitle("title1");
        b1.setContent("content1");
        b1.setUser(user);
        b1.setViewCnt(0L);
        b1.setInDate(new Date());
        b1.setUpDate(new Date());
        boardRepository.save(b1);

        Board2 b2 = new Board2();
        b2.setBno(2L);
        b2.setTitle("title2");
        b2.setContent("content2");
        b2.setUser(user);
        b2.setViewCnt(0L);
        b2.setInDate(new Date());
        b2.setUpDate(new Date());
        boardRepository.save(b2);

        b1 = boardRepository.findById(1L).orElse(null);
        b2 = boardRepository.findById(2L).orElse(null);

        System.out.println("b1 = " + b1);
        System.out.println("b2 = " + b2);

        assertTrue(b1 != null);
        assertTrue(b2 != null);
        
        User2 user2 = userRepository.findById(user.getId()).orElse(null);
        
        // fetch=FetchType.LAZY) 인 경우, 데이터가 없는 것으로 나온다. 그래서 처리를 제대로 해야 한다.
        // 가장 먼저는 @Transactional 을 추가해야 하고. 그 다음은 나중에 스터디해랴
        System.out.println("[[[[[[user2 = " + user2 + "]]]]]]");
        System.out.println("[[[[[[user2.getList = " + user2.getList() + "]]]]]]");
        assertTrue(user2 != null);
    }
}
