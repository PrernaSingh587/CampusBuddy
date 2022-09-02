package com.example.campusbuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
public class CampusbuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusbuddyApplication.class, args);
	}

}
