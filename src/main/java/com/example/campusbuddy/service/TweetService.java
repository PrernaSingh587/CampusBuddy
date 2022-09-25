package com.example.campusbuddy.service;

import com.example.campusbuddy.entity.College;
import com.example.campusbuddy.entity.Tweet;
import com.example.campusbuddy.entity.User;
import com.example.campusbuddy.repository.CollegeRepository;
import com.example.campusbuddy.repository.TweetRepository;
import com.example.campusbuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CollegeRepository collegeRepository;


    public List<Tweet> getAllTweets() {
        //System.out.println("Inside getAllTweets!");
        try {
            List<Tweet>tweetsList = tweetRepository.findAll();
            return tweetsList;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String postTweet(Map<String, String> tweetBody) {
        String userid = tweetBody.get("userid");
        String content = tweetBody.get("content");
        String date = tweetBody.get("date");

        try {
            Optional<User> user_ = userRepository.findById(userid);
            if (user_.isEmpty()) {
                System.out.println("Invalid userid");
                return null;
            }
            User user = user_.get();
            if (user.getCollegeid() == null) {
                System.out.println("User does not have college. Cant add tweet!");
                return null;
            }
            Optional<College> college_ = collegeRepository.findById(user.getCollegeid());
            if (college_.isEmpty()) {
                System.out.println("Invalid collegeid assigned to user.");
                return null;
            }
            College college = college_.get();

            Tweet tweet = new Tweet();
            tweet.setContent(content);
            tweet.setDate(date);
            tweet.setUserid(userid);

            Tweet tweet1 = tweetRepository.save(tweet);

            List<String>miscsid = college.getMiscsId();
            if(miscsid == null) {
                miscsid = new ArrayList<String>();
            }
            miscsid.add(tweet1.getId());
            college.setMiscsId(miscsid);
            collegeRepository.save(college);

            return tweet1.getId();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public int likeTweet(Map<String, String> requestBody) {
        String userid = requestBody.get("userid");
        String tweetid = requestBody.get("tweetid");
        Boolean like = true;
        System.out.println(userid + " " + tweetid);
        try{
            Optional<User> user_ = userRepository.findById(userid);
            Optional<Tweet> tweet_ = tweetRepository.findById(tweetid);
            if(user_.isEmpty() || tweet_.isEmpty()) return -1;
            User user = user_.get();
            Tweet tweet = tweet_.get();
            Set<String>likeIds = tweet.getLikes();
            if(likeIds == null) {
                likeIds = new HashSet<String>();
            }
            if(likeIds.contains(userid)) {
                System.out.println("Disliking..");
                like = false;
                likeIds.remove(userid);
            } else
            likeIds.add(user.getId());

            tweet.setLikes(likeIds);
            tweetRepository.save(tweet);

            return tweet.getLikes().size();
        } catch (Exception e) {
            System.out.println("Can't put like " + e);
            return -1;
        }

    }

    public List<Map<String,String>> postComment(Map<String, String> requestBody) {
        String userid = requestBody.get("userid");
        String tweetid = requestBody.get("tweetid");
        String comment = requestBody.get("comment");
        String date = requestBody.get("date");
        System.out.println("hi");
        try {
            Optional<User> user_ = userRepository.findById(userid);
            Optional<Tweet> tweet_ = tweetRepository.findById(tweetid);
            if (user_.isEmpty() || tweet_.isEmpty()) return null;
            User user = user_.get();
            Tweet tweet = tweet_.get();

            List<Map<String,String>> comments = tweet.getComments();
            if(comments==null) {
                comments = new ArrayList<Map<String,String>>();
            }
            Map<String,String> commentUnit = new HashMap<String,String>();
            commentUnit.put("userid",userid);
            commentUnit.put("comment",comment);
            commentUnit.put("date",date);
            comments.add(commentUnit);
            tweet.setComments(comments);

            tweetRepository.save(tweet);
            return tweet.getComments();
        } catch (Exception e) {
            System.out.println("Can't put comment " + e);
            return null;
        }
    }

    public Map<String,Integer> checkIfTweetLiked(Map<String, String> requestBody) {
        String userid = requestBody.get("userid");
        String tweetid = requestBody.get("tweetid");
        Boolean like = false;
        System.out.println(userid + " " + tweetid);
        Map<String,Integer> mp = new HashMap<String,Integer>();
        mp.put("isLike",0);
        mp.put("noOfLikes",0);
        try{
            Optional<User> user_ = userRepository.findById(userid);
            Optional<Tweet> tweet_ = tweetRepository.findById(tweetid);
            if(user_.isEmpty() || tweet_.isEmpty()) return null;
            User user = user_.get();
            Tweet tweet = tweet_.get();
            Set<String>likeIds = tweet.getLikes();
            if(likeIds == null) {
                return mp;
            }
            if(likeIds.contains(userid)) {
                System.out.println("Liked this tweet already!");
                like = true;
            } else {
                System.out.println("Have not liked already!");
            }

            mp.put("noOfLikes",likeIds.size());
            if(like) {
                mp.put("isLike",1);
            }
            return mp;
        } catch (Exception e) {
            System.out.println("Can't put like " + e);
            return null;
        }

    }

    public List<Map<String,String>> getCommentsForTweetid(Map<String, String> requestBody) {
        String userid = requestBody.get("userid");
        String tweetid = requestBody.get("tweetid");

        try {
            Optional<User> user_ = userRepository.findById(userid);
            Optional<Tweet> tweet_ = tweetRepository.findById(tweetid);
            if (user_.isEmpty() || tweet_.isEmpty()) return null;
            User user = user_.get();
            Tweet tweet = tweet_.get();

            return tweet.getComments();

        } catch (Exception e) {
            System.out.println("Can't put comment " + e);
            return null;
        }
    }
}
