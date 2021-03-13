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

		String ajax = request.getHeader("Ajax");
		System.out.println(ajax);
		String requestUrl = request.getRequestURL().toString();

		if (requestUrl.contains(".html") || requestUrl.contains(".js") || requestUrl.equals("http://localhost:8000/")) {
			System.out.println(requestUrl + "통과");
			return true;
		}

		if (ajax.equals("true")) {
			System.out.println("true");
			return true;
		} else {
			System.out.println("false");
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("Interceptor postHandle");
	}
}
