package com.example.controller;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class CustomErrorController implements ErrorController {
	@Autowired
	Environment env;

	@Override
	public String getErrorPath() { // error경로
		return "/error";
	}

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		int code = Integer.valueOf(status.toString());
		System.out.println("code: " + code);

		log.info("Status:" + status.toString());

		if (code == HttpStatus.NOT_FOUND.value()) {
			model.addAttribute("code", code);
			model.addAttribute("msg", env.getProperty("code.404"));
			model.addAttribute("timestamp", new Date());
		} else {
			model.addAttribute("code", code);
			model.addAttribute("msg", env.getProperty("code.500"));
			model.addAttribute("timestamp", new Date());
		}

		return "thymeleaf/error/error";
	}
}
