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

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardRepositoryTest3 {

	@Autowired
	public EntityManager em;
	
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
	@DisplayName("createQuery 로 JPQL 작성 테스트")
	public void createQueryTest() {
		String query = "SELECT b FROM Board b";
		TypedQuery<Board> tQuery = em.createQuery(query, Board.class);
		List<Board> list = tQuery.getResultList();
		
		assertTrue(list.size() == 100);
	}

	@Test
	@Order(2)
	@DisplayName("@Query 로 JPQL 작성 테스트")
	public void queryAnnoTest() {
		
		List<Board> list = boardRepository.findAllBoard();
		
		assertTrue(list.size() == 100);
	}
	
	@Test
	@Order(3)
	@DisplayName("@Query 로 JPQL 작성 테스트 - 매개변수 순서")
	public void queryParamOrderQueryTest() {
		
		List<Board> list = boardRepository.findByTitleAndWriterUsingParamOrder("title1", "writer1");
		System.out.println("List Size = " + list.size());
		assertTrue(list.size() == 1);
	}
	
	@Test
	@Order(4)
	@DisplayName("@Query 로 JPQL 작성 테스트 - 매개변수 이름")
	public void queryParamNameQueryTest() {
		
		List<Board> list = boardRepository.findByTitleAndWriterUsingParamName("title1", "writer1");
		System.out.println("List Size = " + list.size());
		assertTrue(list.size() == 1);
	}

	@Test
	@Order(5)
	@DisplayName("@Query 로 NativeSQL 작성 테스트")
	public void queryNativeQueryTest() {
		
		List<Board> list = boardRepository.findAllBoardUsingNativeQuery();
		System.out.println("List Size = " + list.size());
		assertTrue(list.size() == 100);
	}

	@Test
	@Order(6)
	@DisplayName("@Query 로 NativeSQL 작성 테스트")
	public void queryNativeQueryToPartialTest() {
		
		List<Object[]> list = boardRepository.findAllBoardByNativeQueryToPartial();
		list.forEach(System.out::println);
		System.out.println("List Size = " + list.size());
		list.stream()
			.map(item -> Arrays.toString(item))
			.forEach(System.out::println);
		assertTrue(list.size() == 100);
	}
}
