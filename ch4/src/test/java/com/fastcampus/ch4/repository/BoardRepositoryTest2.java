package com.fastcampus.ch4.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fastcampus.ch4.entity.Board;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardRepositoryTest2 {

	@Autowired
	private BoardRepository boardRepository;
	
	@BeforeEach
	public void testData() {
		for (int i = 1; i <= 100; i ++) {
			Board board = new Board();
			board.setBno((long) i);
			board.setTitle("title" + i);
			board.setContent("content" + i);
			board.setWriter("writer" + (i % 5));
			boardRepository.save(board);
		}
	}
	
	@Test
	@Order(1)
	public void countTest() {
		
		assertTrue(boardRepository.countAllByWriter("writer1")==20);
	}
	
	@Test
	@Order(2)
	public void findTest() {
		List<Board> list = boardRepository.findByWriter("writer0");
		
		assertTrue(list.size() == 20);
	}
	@Test
	@Order(3)
	public void deleteTest() {
		assertTrue(boardRepository.deleteByWriter("writer3") == 20);
		
		assertTrue(boardRepository.countAllByWriter("writer3") == 0);
				
	}
}
