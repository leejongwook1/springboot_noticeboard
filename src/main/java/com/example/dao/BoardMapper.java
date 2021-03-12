package com.example.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.dto.BoardDto;

@Mapper
public interface BoardMapper {
	
	List<BoardDto> findAll();
	
	BoardDto findByNo(int no);
	
	int insert(BoardDto board);
	
	int update(BoardDto board);
	
	int delete(int no);
}
