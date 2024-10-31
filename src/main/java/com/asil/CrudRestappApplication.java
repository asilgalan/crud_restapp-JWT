package com.asil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:messages.properties")
public class CrudRestappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudRestappApplication.class, args);
	}

}
