package com.fastcampus.ch21.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = "/*")
public class PerformanceFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 1. 전처리 작업
		long startTime = System.currentTimeMillis();
		
		// 2. 서블릿(컨트롤러) 또는 다음 필터
		chain.doFilter(request, response);
		
		// 3. 후처리 작업
		long endTime = System.currentTimeMillis();
		System.out.println("[" + ((HttpServletRequest)request).getRequestURL() + "]");
		System.out.println("time = " + (endTime - startTime));
	}

}
