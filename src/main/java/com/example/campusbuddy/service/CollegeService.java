package com.example.campusbuddy.service;

import com.example.campusbuddy.entity.College;
import com.example.campusbuddy.repository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {

    @Autowired
    CollegeRepository collegeRepository;

    public int addCollegeToDb(College college) {

        try {
            collegeRepository.save(college);

        } catch(Exception e) {
            System.out.println(e);
            return -1;
        }
        return 0;

    }

    public List<College> listColleges() {
        try {
            List<College> collegeList = collegeRepository.findAll();
            return collegeList;
        } catch(Exception e) {
            System.out.println(e);
        }
        return  null;
    }
}
