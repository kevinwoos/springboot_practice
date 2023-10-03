package com.fastcampus.ch3.di4;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;

//@SpringBootApplication

@SpringBootConfiguration	// Configuation 과 동일
//@EnableAutoConfiguration
@ComponentScan

public class Main {

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(Main.class, args);
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		
		Arrays.sort(beanDefinitionNames);
		
		Arrays.stream(beanDefinitionNames)
			.filter(b -> !b.startsWith("org"))
			.forEach(System.out::println);
	}
	
	class MyBean {}
	
	@Bean
	MyBean myBean() { return new MyBean();	}

}
