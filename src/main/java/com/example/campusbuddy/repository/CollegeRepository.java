package com.example.campusbuddy.repository;

import com.example.campusbuddy.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {
    Boolean existsCollegeByCollege(String college);

    College findByCollege(String college);
}
