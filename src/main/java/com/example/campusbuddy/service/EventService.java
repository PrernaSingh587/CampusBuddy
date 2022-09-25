package com.example.campusbuddy.service;

import com.example.campusbuddy.entity.College;
import com.example.campusbuddy.entity.Event;
import com.example.campusbuddy.entity.User;
import com.example.campusbuddy.repository.CollegeRepository;
import com.example.campusbuddy.repository.EventRepository;
import com.example.campusbuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollegeRepository collegeRepository;


    public int postEvent(Map<String, List<String>> requestBody) {
        List<String> userid = requestBody.get("userid");
        List<String> eventType = requestBody.get("eventType");
        List<String> title = requestBody.get("title");
        List<String> hashtags = requestBody.get("hashtags");
        List<String> content = requestBody.get("content");
        List<String> date = requestBody.get("date");

        try {
            Optional<User> user_ = userRepository.findById(userid.get(0));
            if(user_.isEmpty()) {
                System.out.println("Inavlid User");
                return -1;
            }
            User user = user_.get();
            String collegeId = user.getCollegeid();
            Optional<College> college_ = collegeRepository.findById(collegeId);
            if(college_.isEmpty()) {
                System.out.println("Inavlid User's College");
                return -1;
            }

            Event event = new Event();
            event.setCollegeid(collegeId);
            event.setTitle(title.get(0));
            event.setContent(content.get(0));
            event.setUserid(userid.get(0));
            event.setDate(date.get(0));
            event.setHashtags(hashtags);
            event.setEventType(eventType.get(0));
            event.setViews(0);
            event.setSaves(0);

            eventRepository.save(event);
            College college = college_.get();
            List<String>eventsId = college.getEventsId();
            if(eventsId == null) {
                eventsId = new ArrayList<String>();
            }
            eventsId.add(event.getId());
            college.setEventsId(eventsId);
            collegeRepository.save(college);

            return 1;
        } catch (Exception e) {
            System.out.println("Cant post " + e);
            return -1;
        }

    }

    public Map<String,List<Event>> getAllEvents(String collegeid) {
        try {
            Optional<College> college_ = collegeRepository.findById(collegeid);
            if(college_.isEmpty()) {
                System.out.println("Invalid User's College");
                return null;
            }

            List<Event>events = eventRepository.findByCollegeId(collegeid);
            if(events == null) return null;

            List<Event>cultEvents, techEvents, miscEvents, clubEvents;
            cultEvents = eventRepository.findByCollegeIdAndEventType(collegeid,"CULT");
            techEvents = eventRepository.findByCollegeIdAndEventType(collegeid, "TECH");
            clubEvents = eventRepository.findByCollegeIdAndEventType(collegeid, "CLUB");
            miscEvents = eventRepository.findByCollegeIdAndEventType(collegeid, "MISC");

            Map<String,List<Event>> mp = new HashMap<String,List<Event>>();
            mp.put("ALL", events);
            mp.put("CULT", cultEvents);
            mp.put("TECH", techEvents);
            mp.put("MISC", miscEvents);
            mp.put("CLUB", clubEvents);

            return mp;

        }  catch (Exception e) {
            System.out.println("Cant fetch events " + e);
            return null;
        }

    }

    public int increaseViewCount(Map<String, String> requestBody) {
        String userid = requestBody.get("userid");
        String eventid = requestBody.get("eventid");

        System.out.println(userid + eventid);

        try {

            Optional<User> user_ = userRepository.findById(userid);
            if(user_.isEmpty()) {
                System.out.println("No User Found!");
                return -1;
            }

            Optional<Event> event_ = eventRepository.findById(eventid);
            if(event_.isEmpty()) {
                System.out.println("Event Invalid");
            }

            Event event = event_.get();
            Integer viewsCount = event.getViews();
            if(viewsCount == null) {
                viewsCount = 0;
            }
            viewsCount++;
            event.setViews(viewsCount);

            eventRepository.save(event);

            return 1;
        } catch (Exception e) {
            System.out.println("Can't increase view count "+ e);
            return -1;
        }
    }
}
