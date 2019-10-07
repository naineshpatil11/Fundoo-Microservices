package com.bridgelabz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.bridgelabz")
public class NoteMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteMicroservicesApplication.class, args);
	}

}
