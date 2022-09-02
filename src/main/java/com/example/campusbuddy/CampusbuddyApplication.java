package com.example.campusbuddy;

import com.example.campusbuddy.repository.CollegeRepository;
import com.example.campusbuddy.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = CollegeRepository.class)
//@EnableMongoRepositories(basePackageClasses = UserRepository.class)
public class CampusbuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusbuddyApplication.class, args);
	}

}
