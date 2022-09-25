package com.example.campusbuddy.service;


import com.example.campusbuddy.entity.College;
import com.example.campusbuddy.entity.Place;
import com.example.campusbuddy.entity.User;
import com.example.campusbuddy.repository.CollegeRepository;
import com.example.campusbuddy.repository.PlaceRepository;
import com.example.campusbuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlaceService {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    CollegeRepository collegeRepository;

    @Autowired
    UserRepository userRepository;

    public List<Place> getPlacesByCollegeId(String collegeId) {

        try {
            Optional<College> college_ = collegeRepository.findById(collegeId);
            if(college_.isEmpty()) {
                System.out.println("Can't Find COllege id " + collegeId);
                return null;
            }
            College college = college_.get();
            List<String> placesIdList = college.getPlacesId();
            if(placesIdList == null) {
                return null;
            }
            List<Place>placeList = new ArrayList<>();
            for (int i=0;i<placesIdList.size();i++) {
                Optional<Place> place_ = placeRepository.findById(placesIdList.get(i));
                if(place_.isEmpty()) continue;

                Place place = place_.get();
                placeList.add(place);
            }
            return placeList;

        } catch (Exception e) {
            System.out.println("Some Error Occured "+ e);
            return null;
        }
    }

    public int postPlaces(Map<String, List<String>> requestBody) {
        String userid = requestBody.get("userid").get(0);
        String name = requestBody.get("name").get(0);
        String date = requestBody.get("date").get(0);
        String description = requestBody.get("description").get(0);
        String website = requestBody.get("website").get(0);
        Float longtitude = Float.valueOf(requestBody.get("longtitude").get(0));
        Float latitude = Float.valueOf(requestBody.get("latitude").get(0));
        Integer rate =  Integer.valueOf(requestBody.get("rate").get(0));
        String collegeid = requestBody.get("collegeid").get(0);
        List<String>tagIcons = requestBody.get("tagicons");
        List<String>hashtags = requestBody.get("hashtags");
        Integer inOrOut = Integer.valueOf(requestBody.get("inOrOut").get(0));

        try {
            Optional<College> college_ = collegeRepository.findById(collegeid);
            if(college_.isEmpty()) {
                System.out.println("Can't Find COllege id " + collegeid);
                return -1;
            }
            Optional<User> user_ = userRepository.findById(userid);
            if(user_.isEmpty()) {
                System.out.println("Can't Find User id " + userid);
                return -1;
            }
            User user = user_.get();
            College college = college_.get();

            System.out.println("we are here");

            Place place = new Place();
            place.setUserid(userid);
            place.setDate(date);
            place.setDescription(description);
            place.setWebsite(website);
            place.setInOrOut(inOrOut);
            place.setCollegeId(collegeid);
            place.setTagIcon(tagIcons);
            place.setHashtags(hashtags);
            place.setLatitude(latitude);
            place.setLongtitude(longtitude);
            place.setRate(rate);
            place.setName(name);

            placeRepository.save(place);

            List<String>placesId = college.getPlacesId();
            if(placesId == null)
                placesId = new ArrayList<>();
            placesId.add(place.getId());
            college.setPlacesId(placesId);

            collegeRepository.save(college);

            return 1;
        } catch (Exception e) {
            System.out.println("Some Error Occured "+ e);
            return -1;
        }

    }

    public List<Place> getPlacesByCollegeIdAndWhere(String collegeId, Integer where) {

        try {
            Optional<College> college_ = collegeRepository.findById(collegeId);
            if(college_.isEmpty()) {
                System.out.println("Can't Find COllege id " + collegeId);
                return null;
            }
            College college = college_.get();
            List<String> placesIdList = college.getPlacesId();
            if(placesIdList == null) {
                return null;
            }
            List<Place>placeList = new ArrayList<>();
            for (int i=0;i<placesIdList.size();i++) {
                Optional<Place> place_ = placeRepository.findById(placesIdList.get(i));
                if(place_.isEmpty()) continue;

                Place place = place_.get();
                if(place.getInOrOut() == where) {
                    placeList.add(place);
                }
            }
            return placeList;

        } catch (Exception e) {
            System.out.println("Some Error Occured "+ e);
            return null;
        }
    }
}
