package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BoardDto;
import com.example.service.BoardService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class BoardController{
	
	@Autowired
	private BoardService boardService;

	@RequestMapping(value="/boardJson", method=RequestMethod.GET)
	public String showJsonBoard() {
		ArrayList arr = new ArrayList(boardService.findAll());
		String str="[";
		
		for(int i=0; i<arr.size(); i++) {
			Object obj = arr.get(i);
			str += "{";
			str += "\"no\": "+(((BoardDto) obj).getNo())
					+", \"title\": \""+(((BoardDto) obj).getTitle())
					+"\", \"comment\": \""+(((BoardDto) obj).getComment())
					+"\", \"name\": \""+((BoardDto) obj).getName()
					+"\", \"date\": \""+((BoardDto) obj).getDate()
					+"\"";
			if(i+1 == arr.size()) {
				str += "}]";
			} else {
				str += "}, ";
			}
		}
		log.info("GET /INFO : " + str);
		log.warn("GET /WARN : " + str);
		return str;
	}
	
	@RequestMapping(value="/board", method=RequestMethod.GET)
	public List<BoardDto> showBoard() {
		log.info("GET /INFO : " + boardService.findAll());
		log.warn("GET /WARN : " + boardService.findAll());
		return boardService.findAll();
	}
	
	@RequestMapping(value="/board/{no}", method=RequestMethod.GET)
	public BoardDto loadBoard(@RequestBody @PathVariable("no") int no) {
		log.info("GET /INFO : " + no);
		log.warn("GET /WARN : " + no);
		return boardService.findByNo(no);
	}
	
	@RequestMapping(value="/board", method=RequestMethod.POST)
	public int create(@RequestBody BoardDto board) {
		log.info("POST /INFO : " + board.toString());
		log.warn("POST /WARN : " + board.toString());
		return boardService.insert(board);
	}
	
	@RequestMapping(value="/board", method=RequestMethod.PUT)
	public int update(@RequestBody BoardDto board) {
		log.info("PUT /INFO : " + board.toString());
		log.warn("PUT /WARN : " + board.toString());
		return boardService.update(board);
	}
 
	@RequestMapping(value="/board/{no}", method=RequestMethod.DELETE)
	public int delete(@RequestBody @PathVariable("no") int no) {
		log.info("DELETE /INFO : " + no);
		log.warn("DELETE /WARN : " + no);
		return boardService.delete(no);
	}
}
