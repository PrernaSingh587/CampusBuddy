package com.example.campusbuddy.controller;

import com.example.campusbuddy.entity.Tweet;
import com.example.campusbuddy.repository.TweetRepository;
import com.example.campusbuddy.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/tweet")
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private TweetService tweetService;

    @GetMapping(value = "/get-all-tweets")
    public List<Tweet> getAllTweets() {
       return tweetService.getAllTweets();
    }

    @PostMapping(value = "/post-tweet")
    public String postTweet(@RequestBody Map<String,String>tweetBody) {
       return tweetService.postTweet(tweetBody);
    }

    @PostMapping(value = "/like-tweet")
    public int likeTweet(@RequestBody Map<String,String>requestBody) {
        System.out.println("liketweet");
        return tweetService.likeTweet(requestBody);
    }

    @PostMapping(value = "/if-liked")
    public Map<String,Integer> checkIfTweetLiked(@RequestBody Map<String,String>requestBody) {
        System.out.println(" checkIfTweetLiked");
        return tweetService.checkIfTweetLiked(requestBody);
    }

    @PostMapping(value = "/post-comment")
    public List<Map<String,String>> postComment(@RequestBody Map<String,String>requestBody) {
        return tweetService.postComment(requestBody);
    }

    @PostMapping(value = "/get-comment-for-tweetid")
    public List<Map<String,String>> getCommentsForTweetid(@RequestBody Map<String,String>requestBody) {
        return tweetService.getCommentsForTweetid(requestBody);
    }

}
