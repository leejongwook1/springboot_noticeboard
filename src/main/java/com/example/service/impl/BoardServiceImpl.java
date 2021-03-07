package com.example.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.BoardMapper;
import com.example.dto.BoardDto;
import com.example.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<BoardDto> findAll(){
		return boardMapper.findAll();
	}

	@Override
	public void insert(BoardDto board) {
		boardMapper.insert(board);
	}

	@Override
	public void update(BoardDto board) {
		boardMapper.update(board);
	}

	@Override
	public void delete(int no) {
		boardMapper.delete(no);
	}
}
