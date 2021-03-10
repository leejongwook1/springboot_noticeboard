package com.example.service;

import java.util.List;
import com.example.dto.BoardDto;

public interface BoardService {
	public List<BoardDto> findAll();
	public BoardDto findByNo(int no);
	public int insert(BoardDto board);
	public int update(BoardDto board);
	public int delete(int no);
}
