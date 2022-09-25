package com.example.campusbuddy.service;

import com.example.campusbuddy.entity.College;
import com.example.campusbuddy.repository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Map<String,String>> listCollegesNames() {

        try {
            List<College> collegeList = collegeRepository.findAll();
            if(collegeList == null || collegeList.size() == 0) return null;

            List<Map<String,String>>collegeListNames = new ArrayList<>();
            for(int i=0;i<collegeList.size();i++) {
                Map<String,String>mp = new HashMap<String,String>();
                mp.put("label", collegeList.get(i).getCollege());
                mp.put("value", collegeList.get(i).getId());
                collegeListNames.add(mp);
            }
            return collegeListNames;
        } catch(Exception e) {
            System.out.println(e);
        }
        return  null;
    }
}
