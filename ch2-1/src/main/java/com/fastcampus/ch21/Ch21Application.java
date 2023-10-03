package com.fastcampus.ch21;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Ch21Application {

	public static void main(String[] args) {
		SpringApplication.run(Ch21Application.class, args);
	}

}
