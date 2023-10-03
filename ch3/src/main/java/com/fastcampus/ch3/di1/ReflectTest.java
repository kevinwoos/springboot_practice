package com.fastcampus.ch3.di1;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {

	public static void main(String[] args) throws Exception {
		Car car = new Car();
		
		Class carClass  = car.getClass();
		carClass = Car.class;
		carClass = Class.forName("com.fastcampus.ch3.di1.Car");
		
		// 1. Class객체 로 부터 객체 생성하기 
		Car car2 = (Car) carClass.newInstance();
		System.out.println("car2 = " + car2);
		
		// 2. 클래스에 선언된 멤버변수와 메소드 목록 얻기
		Method[] method = carClass.getDeclaredMethods();
		Method[] methods = carClass.getMethods();
		Field[] mv = carClass.getDeclaredFields();
		
		for(Field item : mv) {
			System.out.println(item.getName());
		}
		for(Method item : method) {
			System.out.println(item.getName());
		}
		
		Method dynamicMethod = carClass.getMethod("getString");
		System.out.println("Dynamic Method = " + dynamicMethod);
		System.out.println("method = " + dynamicMethod.invoke(car2));
		
	}

}
