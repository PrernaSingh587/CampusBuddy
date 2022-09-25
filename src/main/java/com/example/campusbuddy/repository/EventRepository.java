package com.example.campusbuddy.repository;

import com.example.campusbuddy.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EventRepository extends MongoRepository<Event,String> {

    @Query("{'collegeid' : ?0 }")
    List<Event> findByCollegeId(String collegeid);

    @Query("{'collegeid' : ?0, 'eventType' : ?1 }")
    List<Event> findByCollegeIdAndEventType(String collegeid, String eventType);

}
