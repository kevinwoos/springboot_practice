package com.fastcampus.ch3.di3;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
class Car {
	
	public Car(Engine engine, Door door) {
		super();
		this.engine = engine;
		this.door = door;
	}

	Engine engine;
	Door door;
	
	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public Door getDoor() {
		return door;
	}

	public void setDoor(Door door) {
		this.door = door;
	}

	@Override
	public String toString() {
		return "Car [" +
				"engine = " + engine + 
				", door = " + door + 
				"]";
	}
}

//@ComponentScan
@Component
class Engine {}

@Component
class Door {}

public class Main {

	public static void main(String[] args) {
		// AC를 설정 - AC의 설정파일은 AppConfig.class 자바실행
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

		Car car = ac.getBean("car", Car.class);
		Engine engine = (Engine) ac.getBean("engine");
		Door door = ac.getBean(Door.class);
		
		System.out.println("engien = " + engine);
		
		Engine engine2 = ac.getBean(Engine.class);
		System.out.println("engien2 = " + engine2);
		
//		car.setEngine(engine);
//		car.setDoor(door);
		
		System.out.println("car = " + car);
		
		System.out.println("ac.getBeanDefinitionCount " + ac.getBeanDefinitionCount());
		System.out.println("ac.getBeanDefinitionNames " + Arrays.toString(ac.getBeanDefinitionNames()));
		
		
		SystemInfo info = ac.getBean(SystemInfo.class);
		System.out.println("Sys Info = " + info);
	}

}
