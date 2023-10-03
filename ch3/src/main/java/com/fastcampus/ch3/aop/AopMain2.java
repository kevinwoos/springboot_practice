package com.fastcampus.ch3.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

public class AopMain2 {

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(Config.class);
		
		MyMath mm = ac.getBean("myMath", MyMath.class);
		
		mm.add(1, 2);
		mm.add(1, 2, 3);
		mm.multiply(1, 2);
	}

}

@EnableAspectJAutoProxy
@ComponentScan
@Configuration
class Config {
	
}

@Component
@Aspect
class LoggingAdvice {
	
	@Around("execution(* com.fastcampus.ch3.aop.MyMath.add*(..))")	//aop.MyMath.add*(..)
	public Object methodClassLog(ProceedingJoinPoint pjp) throws Throwable {
		
		long start = System.currentTimeMillis();
		System.out.println("[start]" + pjp.getSignature().getName() + Arrays.toString(pjp.getArgs()));
		
		Object result = pjp.proceed();
		
		System.out.println("result = " + result);
		
		System.out.println("[end]" + (System.currentTimeMillis() - start) + " ms");
		
		return result;
	}
}

@Component
class MyMath {
	int add(int a, int b) {
		int result = a + b;
		
		return result;
	}
	int add(int a, int b, int c) {
		int result = a + b + c;
		
		return result;
	}
	int subtract(int a, int b) {
		int result = a - b;
		
		return result;
	}
	int multiply(int a, int b) {
		int result = a * b;
		
		return result;
	}

}