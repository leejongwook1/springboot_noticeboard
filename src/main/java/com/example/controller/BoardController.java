package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BoardDto;
import com.example.dto.ResponseVo;
import com.example.service.BoardService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/boardJson", method = RequestMethod.GET)
	public String showJsonBoard() {
		List<BoardDto> arr = boardService.findAll();
		String str = "[";

		for (int i = 0; i < arr.size(); i++) {
			Object obj = arr.get(i);
			str += "{";
			str += "\"no\": " + (((BoardDto) obj).getNo()) + ", \"title\": \"" + (((BoardDto) obj).getTitle())
					+ "\", \"comment\": \"" + (((BoardDto) obj).getComment()) + "\", \"name\": \""
					+ ((BoardDto) obj).getName() + "\", \"date\": \"" + ((BoardDto) obj).getDate() + "\"";
			if (i + 1 == arr.size()) {
				str += "}]";
			} else {
				str += "}, ";
			}
		}
		log.info("GET /INFO : " + str);
		log.warn("GET /WARN : " + str);
		return str;
	}

	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public ResponseVo showBoard() {
		ResponseVo json = new ResponseVo();
		List<BoardDto> resultBoards = boardService.findAll();

		if (resultBoards.size() != 0) {
			json.setResponseVo(resultBoards);
			json.setStatus("200");
			json.setMessage("OK");
		} else {
			json.setStatus("404");
			json.setMessage("Not Found");
		}
		return json;
	}

	@RequestMapping(value = "/board/{no}", method = RequestMethod.GET)
	public ResponseVo loadBoard(@RequestBody @PathVariable("no") int no) {
		ResponseVo json = new ResponseVo();
		BoardDto resultBoard = boardService.findByNo(no);

		if (resultBoard != null) {
			json.setResponseVo(resultBoard);
			json.setStatus("200");
			json.setMessage("OK");
		} else {
			json.setStatus("404");
			json.setMessage("Not Found");
		}
		return json;
	}

	@RequestMapping(value = "/board", method = RequestMethod.POST)
	public ResponseVo create(@RequestBody BoardDto board) {
		ResponseVo json = new ResponseVo();
		int resultNum = boardService.insert(board);

		if (resultNum != 0) {
			json.setResponseVo(resultNum);
			json.setStatus("200");
			json.setMessage("OK");
		} else {
			json.setStatus("404");
			json.setMessage("Not Found");
		}
		return json;
	}

	@RequestMapping(value = "/board", method = RequestMethod.PUT)
	public ResponseVo update(@RequestBody BoardDto board) {
		ResponseVo json = new ResponseVo();
		int resultNum = boardService.update(board);

		if (resultNum != 0) {
			json.setResponseVo(resultNum);
			json.setStatus("200");
			json.setMessage("OK");
		} else {
			json.setStatus("404");
			json.setMessage("Not Found");
		}
		return json;
	}

	@RequestMapping(value = "/board/{no}", method = RequestMethod.DELETE)
	public ResponseVo delete(@RequestBody @PathVariable("no") int no) {
		ResponseVo json = new ResponseVo();
		int resultNum = boardService.delete(no);

		if (resultNum != 0) {
			json.setResponseVo(resultNum);
			json.setStatus("200");
			json.setMessage("OK");
		} else {
			json.setStatus("404");
			json.setMessage("Not Found");
		}
		return json;
	}
}
