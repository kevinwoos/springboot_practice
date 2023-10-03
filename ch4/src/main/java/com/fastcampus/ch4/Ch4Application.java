package com.fastcampus.ch4;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fastcampus.ch4.entity.User;

@SpringBootApplication
public class Ch4Application implements CommandLineRunner {
	
	@Autowired
	EntityManagerFactory emf;

	public static void main(String[] args) {
//		SpringApplication.run(Ch4Application.class, args);
		
		SpringApplication app = new SpringApplication(Ch4Application.class);
//		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		EntityManager em = emf.createEntityManager();
		EntityManager em2 = emf.createEntityManager();
		System.out.println("emf = " + emf);
		System.out.println("em = " + em);
		System.out.println("em2 = " + em2);
		
		EntityTransaction tx = em.getTransaction();
		 
		User user = new User();
		user.setId("aaa");
		user.setPassword("1234");
		user.setName("lee");
		user.setEmail("aaa@aaa.com");
		user.setInDate(new Date());
		user.setUpDate(new Date());
		
		tx.begin();
		em.persist(user);
		em.persist(user);
		
		user.setPassword("4321");
		user.setEmail("bbb@bbb.com");
		tx.commit();
		
		User user2 = em.find(User.class, "aaa");
		
		System.out.println("user == user2 => " + (user == user2));
		
		User user3 = em.find(User.class, "bbb");
		
		System.out.println("user == user3 => " + (user == user3));
		
		tx.begin();
		em.remove(user);
		System.out.println("user =" + user);

		User user4 = em.find(User.class, "aaa");
		System.out.println("user4 =" + user4);

		tx.commit();

		User user5 = em.find(User.class, "aaa");
		System.out.println("user5 =" + user5);
		
	}

}
