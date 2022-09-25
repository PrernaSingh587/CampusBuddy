package com.example.campusbuddy.repository;

import com.example.campusbuddy.entity.Place;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends MongoRepository<Place,String> {

}
