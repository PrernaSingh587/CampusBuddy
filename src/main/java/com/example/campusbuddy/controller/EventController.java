package com.example.campusbuddy.controller;


import com.example.campusbuddy.entity.Event;
import com.example.campusbuddy.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/event")
public class EventController {

    @Autowired
    public EventService eventService;

    @PostMapping("/post-event")
    public int postEvent(@RequestBody  Map<String, List<String>> requestBody) {
        return eventService.postEvent(requestBody);
    }

    @PostMapping("/get-all-events")
    public Map<String,List<Event>> getAllEvents(@RequestBody Map<String,String> collegeid) {
        return eventService.getAllEvents(collegeid.get("collegeid"));
    }

    @PostMapping("/increase-view-count")
    public int increaseViewCount(@RequestBody Map<String,String> requestBody) {
        return eventService.increaseViewCount(requestBody);
    }

}
