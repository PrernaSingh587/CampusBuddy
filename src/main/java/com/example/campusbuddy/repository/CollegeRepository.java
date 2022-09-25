package com.example.campusbuddy.repository;

import com.example.campusbuddy.entity.College;
import com.example.campusbuddy.entity.Place;
import com.example.campusbuddy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollegeRepository extends MongoRepository<College, String> {

    Boolean existsCollegeByCollege(String college);

}
