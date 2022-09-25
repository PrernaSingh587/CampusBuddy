package com.example.campusbuddy.entity;

import com.example.campusbuddy.util.StringListConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@Convert(converter = StringListConverter.class)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String collegeid;
    private String content;
    private String title;
    private String userid;
    private String date;
    private List<String> hashtags;
    private String eventType;
    private int views;
    private int saves;
}
