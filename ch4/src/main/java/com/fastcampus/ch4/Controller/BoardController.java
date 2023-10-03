package com.fastcampus.ch4.Controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.fastcampus.ch4.entity.Board2;
import com.fastcampus.ch4.entity.User2;
import com.fastcampus.ch4.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@Value("${uploadPath}")
	private String uploadPath;
	
	@GetMapping("/uploadForm")
	public String showUploadForm() {
		return "/board/uploadFile";
	}
	
	@PostMapping("/uploadFile")
	public void uploadFile(MultipartFile[] files) throws Exception {
		for(MultipartFile file : files) {
			System.out.println("file = [" + file.getOriginalFilename() + " : " + file.getSize() + "]");
			
			File upFile = new File(uploadPath, file.getOriginalFilename());
			
			file.transferTo(upFile);
			
		}
	}
	
	@GetMapping("/list")
	public String getList(Model model) {
		List<Board2> list = boardService.getList();
		
		model.addAttribute("list", list);
		
		return "/board/list";
	}
	
	@GetMapping("/read")
	public String read(Long bno, Model model) {
		Board2 board = boardService.read(bno);
		
		model.addAttribute("board", board);
		
		return "/board/read";
	}
	
	@PostMapping("/remove")
	public String remove(Long bno) {
		boardService.remove(bno);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/write")
	public String showWriteForm(Model model) {
		Board2 board = new Board2();
		User2 user = new User2();
		
		user.setId("bbb");
		board.setUser(user);
		
		model.addAttribute("board", board);
		
		return "/board/write";
	}
	
	@PostMapping("/write")
	public String write(Board2 board) {
		board.setBno(11L);
		
		User2 user = new User2();
		user.setId("aaa");
		
		board.setUser(user);
		board.setViewCnt(0L);
		board.setInDate(new Date());
		board.setUpDate(new Date());
		
		boardService.write(board);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/modify")
	public String modify(Long bno, Model model) {
		Board2 board = boardService.read(bno);
		
		model.addAttribute("board", board);
		
		return "/board/write";
	}
	
	@PostMapping("modify")
	public String modify(Board2 board) {
		boardService.modify(board);
		
		return "redirect:/board/list";
	}
}
