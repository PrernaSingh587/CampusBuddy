package com.example.campusbuddy;

import com.example.campusbuddy.repository.TestRepository1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = TestRepository1.class)
public class CampusbuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusbuddyApplication.class, args);
	}

}
