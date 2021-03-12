package com.example.dto;

import java.util.List;

import lombok.Data;

@Data
public class BoardVo {
	private List<BoardDto> boardVo;
	private String message;
	private String error;
}
