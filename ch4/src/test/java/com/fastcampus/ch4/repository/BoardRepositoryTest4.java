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
import com.fastcampus.ch4.entity.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.fastcampus.ch4.entity.QBoard.board;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardRepositoryTest4 {

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
	@DisplayName("QueryDSL 쿼리 작성 테스트 - 간단한 쿼리 작성")
	public void queryDslTest1() {
		// import static com.fastcampus.ch4.entity.QBoard.board; 를 선언하면 아래에 문장 주석처리 가능
		// QBoard board = QBoard.board;
		
		// 1. JPAQueryFactory를 생성
		JPAQueryFactory qf = new JPAQueryFactory(em);
		// 2. 쿼리 작성
		JPAQuery<Board> query = qf.selectFrom(board)
				.where(board.title.eq("title1"));
		
		// 3. 쿼리 실행
		List<Board> list = query.fetch();
		
		System.out.println("List Size = " + list.size());

//		list.stream()
//			.map(item -> Arrays.toString(item))
//			.forEach(System.out::println);
		list.forEach(System.out::println);
		assertTrue(list.size() == 1);
	}

	@Test
	@Order(2)
	@DisplayName("QueryDSL 쿼리 작성 테스트 - 복잡한 쿼리 작성")
	public void queryDslTest2() {
		// import static com.fastcampus.ch4.entity.QBoard.board; 를 선언하면 아래에 문장 주석처리 가능
		// QBoard board = QBoard.board;
		
		// 1. JPAQueryFactory를 생성
		JPAQueryFactory qf = new JPAQueryFactory(em);
		// 2. 쿼리 작성
		JPAQuery<Tuple> query = qf.select(board.writer, board.viewCnt.count()).from(board)
				.where(board.title.notLike("title1"))
				.where(board.writer.eq("writer1"))
				.where(board.content.contains("content1"))
				.groupBy(board.writer)
//				.having(board.viewCnt.count().gt(1))
				.orderBy(board.writer.desc());

		
		// 3. 쿼리 실행
		List<Tuple> list = query.fetch();
		
		System.out.println("List Size = " + list.size());

//		list.stream()
//			.map(item -> Arrays.toString(item))
//			.forEach(System.out::println);
		list.forEach(System.out::println);
		assertTrue(list.size() >= 1);
	}

	@Test
	@Order(3)
	@DisplayName("QueryDSL 쿼리 작성 테스트 - 동적 쿼리 작성")
	public void queryDslTest3() {
		String searchBy = "TC";
		String keyword = "1";
		
		keyword = "%" + keyword + "%";
		
		BooleanBuilder builder = new BooleanBuilder();
		
		// 0. 동적으로 조건을 달리 생성
		if(searchBy.equalsIgnoreCase("T"))
			builder.and(board.title.like(keyword));
		else if(searchBy.equalsIgnoreCase("C"))
			builder.and(board.content.like("TC"));
		else if(searchBy.equalsIgnoreCase("TC"))
			builder.and(board.title.like(keyword).or(board.content.like(keyword)));
				
		// 1. JPAQueryFactory를 생성
		JPAQueryFactory qf = new JPAQueryFactory(em);
		// 2. 쿼리 작성
		JPAQuery query = qf.selectFrom(board)
				.where(builder)
				.orderBy(board.upDate.desc());

		
		// 3. 쿼리 실행
		List<Board> list = query.fetch();
		
		System.out.println("List Size = " + list.size());

		list.forEach(System.out::println);
		assertTrue(list.size() == 20);
	}
}
