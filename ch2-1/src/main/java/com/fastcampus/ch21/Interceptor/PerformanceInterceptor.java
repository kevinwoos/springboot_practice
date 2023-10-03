package com.fastcampus.ch21.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PerformanceInterceptor implements HandlerInterceptor{

	// 여러 쓰레드에서 사용할 수 있게 코딩해야 하나, 여기서는 쉽게 request 에 변수값을 저장한다. 
	// 그래서 여기서는 아래 iv를 주석 처리 
	// long startTime;		// iv(인스턴스 변수). 싱글톤이기 때문에 여러 쓰레드가 하나의 객체를 공유

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. 전처리 작업
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		
		System.out.println("[[Start Time ]]");
		
		// handler : 요청과 연결된 컨트롤러의 메소드 
		HandlerMethod method = (HandlerMethod) handler;
		System.out.println("[[method.getMethod = " + method.getMethod() + "]]");	// URL하고 연결된 메소드
		System.out.println("[[method.getBean = " + method.getBean() + "]]");		// 메소드가 포함된 컨트롤러
		
		// if return true, 다음 인터셉터나 컨트롤러를 호출, false면 호출안함
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 2. 후처리 작업
		long startTime = (long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		System.out.println("[[" + ((HttpServletRequest)request).getRequestURL() + "]]");
		System.out.println("time = " + (endTime - startTime));
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}
