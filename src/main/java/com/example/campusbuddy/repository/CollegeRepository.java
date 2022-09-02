package com.example.campusbuddy.repository;

import com.example.campusbuddy.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends MongoRepository<College, Long> {

    Boolean existsCollegeByCollege(String college);

    @Query("{ 'college' : ?0 }")
    College findByCollege(String college);
}
