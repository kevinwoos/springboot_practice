package com.fastcampus.ch3.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.transaction.annotation.Transactional;

public class AopMain {

	public static void main(String[] args) throws Exception {
		Class myClass = Class.forName("com.fastcampus.ch3.aop.MyClass");
		Object o = myClass.newInstance();
		
		MyAdvice myAdvice = new MyAdvice();
		
		for(Method m : myClass.getDeclaredMethods()) {
			myAdvice.invoke(m, o);
		}
	}

}

class MyAdvice {
	Pattern p = Pattern.compile("a.*");
	
	boolean matches(Method m) {
		Matcher matcher = p.matcher(m.getName());
		
		return matcher.matches();
	}
	
	void invoke(Method m, Object obj, Object... args) throws Exception {
		
		if(matches(m) || m.getAnnotation(Transactional.class) != null)
			System.out.println("[before] {");
		
		m.invoke(obj, args);
		
		if(matches(m) || m.getAnnotation(Transactional.class) != null)
			System.out.println("}[after]");
		
	}
}

class MyClass {
	void aaa() {
		System.out.println("aaa() is called.");
	}
	
	@Transactional
	void bbb() {
		System.out.println("bbb() is called.");
	}
	void ccc() {
		System.out.println("ccc() is called.");
	}
}