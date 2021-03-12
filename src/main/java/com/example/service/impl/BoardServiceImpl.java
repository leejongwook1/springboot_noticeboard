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
	public BoardDto findByNo(int no) {
		return boardMapper.findByNo(no);
	}

	@Override
	public int insert(BoardDto board) {
		return boardMapper.insert(board);
	}

	@Override
	public int update(BoardDto board) {
		return boardMapper.update(board);
	}

	@Override
	public int delete(int no) {
		return boardMapper.delete(no);
	}
}
