package com.example.service;

import java.util.List;
import com.example.dto.BoardDto;

public interface BoardService {
	public List<BoardDto> findAll();
	public void insert(BoardDto board);
	public void update(BoardDto board);
	public void delete(int no);
}
