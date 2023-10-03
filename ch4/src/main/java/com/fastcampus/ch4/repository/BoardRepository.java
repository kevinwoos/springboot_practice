package com.fastcampus.ch4.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fastcampus.ch4.entity.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {

	int countAllByWriter(String writer);
	
	List<Board> findByWriter(String writer);
	
	List<Board> findByTitleAndWriter(String title, String writer);
	
	@Transactional
	int deleteByWriter(String writer);
	
	@Query("SELECT b FROM Board b")
	List<Board> findAllBoard();
	
	@Query("SELECT b FROM Board b WHERE b.title=?1 AND b.writer=?2")
	List<Board> findByTitleAndWriterUsingParamOrder(String title, String writer);

	@Query("SELECT b FROM Board b WHERE b.title=:title AND b.writer=:writer")
	List<Board> findByTitleAndWriterUsingParamName(String title, String writer);

	@Query(value = "SELECT * FROM BOARD", nativeQuery = true)
	List<Board> findAllBoardUsingNativeQuery();

	@Query(value = "SELECT TITLE, WRITER FROM BOARD", nativeQuery = true)
	List<Object[]> findAllBoardByNativeQueryToPartial();
}
