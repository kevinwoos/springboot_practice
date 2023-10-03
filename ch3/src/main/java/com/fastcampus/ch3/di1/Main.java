package com.fastcampus.ch3.di1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

class Car {
	public String getString() {
		return "TEST!!";
	}
}

class SportsCar extends Car {}

class Truck extends Car {}

class Engine {}
class Door {}

public class Main {
	public static void main(String[] args) throws Exception {
//		Car car = new SportsCar();
		Car car = getCar();
		
		car = getCar();
		
		System.out.println("car = " + car);
		
		car = (Car) getObject("car");
		System.out.println("car = " + car);

		Engine engine = (Engine) getObject("engine");
		System.out.println("engine = " + engine);

	}
	
	static Object getOldObject(String key) {
		if(key.equals("car")) 
			return new SportsCar();
		else if(key.equals("engine"))
			return new Engine();
		else return new Door();
	}
	
	static Object getObject(String key) throws Exception {
		Properties prop = new Properties();
		prop.load(new FileReader("config.txt"));
		String className = prop.getProperty(key);
		Class clazz = Class.forName(className);
		
		return clazz.newInstance();
	}
	
	static Car getCar() {
		return new Truck();
	}
}
