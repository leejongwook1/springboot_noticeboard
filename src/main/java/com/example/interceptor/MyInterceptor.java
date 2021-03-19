package com.example.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MyInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("Interceptor preHandle");

		String requestUrl = request.getRequestURL().toString();
		System.out.println(requestUrl);

		if (requestUrl.equals("http://localhost:8000/favicon.ico") || requestUrl.contains("/error")) {
			System.out.println(requestUrl + "통과");
			return true;
		}

		String ajax = request.getHeader("Ajax");
		System.out.println(ajax);

		if ("true".equals(ajax)) {
			System.out.println("true");
			return true;
		}
		System.out.println("false");
		// response.setHeader("Ajax", "true");
		response.sendRedirect(request.getContextPath() + "/error1");
		// response.sendError(404);
		return false;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("Interceptor postHandle");
	}
}
