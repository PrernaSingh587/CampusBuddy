package com.example.campusbuddy.controller;


import com.example.campusbuddy.entity.Place;
import com.example.campusbuddy.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/place")
public class PlaceController {

    @Autowired
    PlaceService placeService;

    @GetMapping(value = "/get-places-college/{collegeId}")
    public List<Place> getPlacesByCollegeId(@PathVariable String collegeId) {
        return placeService.getPlacesByCollegeId(collegeId);
    }

    @GetMapping(value = "/get-places-college/{collegeId}/{where}")
    public List<Place> getPlacesByCollegeId(@PathVariable String collegeId, @PathVariable Integer where) {
        return placeService.getPlacesByCollegeIdAndWhere(collegeId, where);
    }

    @PostMapping(value = "/post-place")
    public int postPlaces(@RequestBody Map<String,List<String>> requestBody) {
        return placeService.postPlaces(requestBody);
    }
}
