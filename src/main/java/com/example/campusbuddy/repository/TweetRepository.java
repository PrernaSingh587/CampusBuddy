package com.example.campusbuddy.repository;

import com.example.campusbuddy.entity.Tweet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TweetRepository extends MongoRepository<Tweet,String> {

}
