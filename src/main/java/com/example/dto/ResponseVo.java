package com.example.dto;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Repository
@Data
public class ResponseVo {
	private Object responseVo;
	private String code;
	private String message;
}
