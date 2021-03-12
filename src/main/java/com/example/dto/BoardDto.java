package com.example.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardDto {
	private int no;
	private String title;
	private String comment;
	private String name;
	private Date date;
}
