package com.example.dao;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.dto.BoardDto;

@Mapper
public interface BoardMapper {
	
	@Select("SELECT * FROM board")
	public List<BoardDto> findAll();
	
	@Insert("INSERT INTO board (title, comment, name, date) VALUES(#{title}, #{comment}, #{name}, #{date})")
	public void insert(BoardDto board);
	
	@Update("UPDATE board SET title=#{title}, comment=#{comment}, date=#{date} where no=#{no}")
	public void update(BoardDto board);
	
	@Delete("DELETE FROM board where no=#{no}")
	public void delete(@Param("no") int no);
}
