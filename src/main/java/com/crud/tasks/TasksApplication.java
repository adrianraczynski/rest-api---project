package com.crud.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class TasksApplication  extends SpringBootServletInitializer  {		// extands.....	musi być zakomentowane, ponieważ dla modułu 19.1 odkomentowałee
	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}


	@Override		// ta metoda musi być zakomentowana, ponieważ dla modułu 19.1 odkomentowałem ją
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TasksApplication.class);
	}
}
