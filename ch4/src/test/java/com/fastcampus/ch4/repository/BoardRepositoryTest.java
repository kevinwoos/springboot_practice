package com.fastcampus.ch4.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fastcampus.ch4.entity.Board;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	@Order(1)
	public void insertTest() {
		Board board = new Board();
		board.setBno(1L);
		board.setTitle("Test Title");
		board.setContent("This is Test");
		board.setWriter("aaa");
		
		boardRepository.save(board);
	}
	
	@Test
	@Order(2)
	public void selectTest() {
//		Board board = boardRepository.findById(1L).get();	// 값이 없을 때 예외발생
		Board board = boardRepository.findById(1L).orElse(null);
		
		assertTrue(board != null);
	}
	@Test
	@Order(3)
	public void updateTest() {
		Board board = boardRepository.findById(1L).orElse(null);
		assertTrue(board != null);
		
		board.setWriter("bbb");
		boardRepository.save(board);
		
		Board board2 = boardRepository.findById(1L).orElse(new Board());
		assertTrue(board.getWriter().equals(board2.getWriter()));
	}
	
	@Test
	@Order(4)
	public void deleteTest() {
		Board board = boardRepository.findById(1L).orElse(null);
		assertTrue(board != null);
		
		boardRepository.delete(board);
		
		Board board2 = boardRepository.findById(1L).orElse(null);
		assertTrue(board2 == null);
	}

}
