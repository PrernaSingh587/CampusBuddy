package com.example.campusbuddy.controller;

import com.example.campusbuddy.entity.College;
import com.example.campusbuddy.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/college")
public class CollegeController {

    @Autowired
    CollegeService collegeService;

    //done
    @PostMapping("/add-college")
    public int addCollegeToDb(@RequestBody College college) {
        int res;
        res = collegeService.addCollegeToDb(college);
        return res;
    }

    //done
    @GetMapping("/list-colleges")
    public List<College> listColleges() {
        return collegeService.listColleges();
    }

    //done
    @GetMapping("/list-college-names")
    public List<Map<String,String>> listCollegesNames() {
        return collegeService.listCollegesNames();
    }

}
