package com.fastcampus.ch4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastcampus.ch4.entity.Board;
import com.fastcampus.ch4.entity.Board2;
import com.fastcampus.ch4.repository.BoardRepository;
import com.fastcampus.ch4.repository.BoardRepository2;

@Service
public class BoardService {

	@Autowired
	private BoardRepository2 boardRepository;
	
	public List<Board2> getList() {
		return (List<Board2>) boardRepository.findAll();
	}
	
	public Board2 write(Board2 board) {
		return boardRepository.save(board);
	}
	
	public Board2 read(Long bno) {
		return boardRepository.findById(bno).orElse(null);
	}
	
	public Board2 modify(Board2 newBoard) {
		Board2 board = boardRepository.findById(newBoard.getBno()).orElse(null);
		
		if(board == null) return null;
		
		board.setTitle(newBoard.getTitle());
		board.setContent(newBoard.getContent());
		
		return boardRepository.save(board);
	}
	
	public Board2 remove(Long bno) {
		Board2 board = boardRepository.findById(bno).orElse(null);
		
		if(board == null) return null;
		
		boardRepository.deleteById(bno);
		
		return board;
	}
}
