package com.example.controller;

import org.springframework.stereotype.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.dto.BoardDto;
import com.example.service.BoardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class BoardController{
	
	private static Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board")
	public ModelAndView showBoard() throws Exception {
		List<BoardDto> boardList = boardService.findAll();
		ModelAndView boardPage = new ModelAndView("board/showBoard");
		boardPage.addObject("boardList", boardList);
		return boardPage;
	}
	
	@PostMapping("/board")
	public void create(BoardDto board) throws Exception{
		logger.info("POST /board : " + board.toString());
		boardService.insert(board);
	}
	
	@PutMapping("/board")
	public void update(BoardDto board) throws Exception{
		logger.info("PUT data : " + board.toString());
		boardService.update(board);
	}

	//PathVariable 글번호에 참조하기위해
	@DeleteMapping("/board/{no}")
	public void delete(@PathVariable("no") int no) throws Exception{
		logger.info("DELETE no : " + no);
		boardService.delete(no);
	}
}
