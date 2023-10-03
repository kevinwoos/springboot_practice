package com.fastcampus.ch4.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fastcampus.ch4.entity.Board;
import com.fastcampus.ch4.entity.Board2;
import com.fastcampus.ch4.entity.User2;
import com.fastcampus.ch4.repository.BoardRepository;
import com.fastcampus.ch4.repository.BoardRepository2;
import com.fastcampus.ch4.repository.UserRepository2;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardServiceTest {

	@Autowired
	//private BoardRepository2 boardRepository;
	BoardService boardService;
	
	@Autowired
	private UserRepository2 userRepository;
	
	@BeforeEach
	public void init() {
		for (Long i = 1L; i <= 10L; i ++) {
			Board2 board = new Board2();
			board.setBno(i);
			board.setTitle("title" + i);
			board.setContent("content" + i);
			
			User2 user = new User2();
			user.setId("aaa");
			userRepository.save(user);
			
			board.setUser(user);
			board.setViewCnt(0L);
			board.setInDate(new Date());
			board.setUpDate(new Date());
			
			// boardRepository.save(board);
			boardService.write(board);
			
		}
	}
	
	@Test
	@Order(1)
	public void getListTest() {
		List<Board2> list = boardService.getList();
		
		System.out.println("list = " + list);
		
		assertTrue(list.size() == 10);
	}

	@Test
	@Order(2)
	public void writeAndReadTest() {
		User2 u = new User2();
		
		u.setId("bbb");
		userRepository.save(u);
		
		Board2 b = new Board2();
		
		b.setBno(11L);
		b.setTitle("new Title");
		b.setContent("new Content");
		b.setViewCnt(11L);
		b.setInDate(new Date());
		b.setUpDate(new Date());
		
		b.setUser(u);
		
		boardService.write(b);
		
		Board2 b2 = boardService.read(b.getBno());
		
		System.out.println("b2 = " + b2);
		
		assertTrue(b2 != null && b2.getBno() == 11);
	}

	@Test
	@Order(3)
	public void modifyTest() {

		Board2 b = boardService.read(2L);
		
		System.out.println("b = " + b);
		
		assertTrue(b != null && b.getBno() == 2L);
		
		b.setContent("new Content");
		b.setTitle("new Title");
		
		Board2 b2 = boardService.modify(b);
		
		assertTrue(b2.getBno() == b.getBno());
		assertTrue(b2.getTitle().equals(b.getTitle()));
		assertTrue(b2.getContent().equals(b.getContent()));
		
		Board2 b3 = boardService.read(2L);

		assertTrue(b3.getBno() == b.getBno());
		assertTrue(b3.getTitle().equals(b.getTitle()));
		assertTrue(b3.getContent().equals(b.getContent()));
}
}


