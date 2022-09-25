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
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String website;
    private float longtitude;
    private float latitude;
    private int rate;
    private List<String> hashtags;
    private List<String> tagIcon;
    private String description;
    private String userid;
    private String date;
    private int inOrOut;
    private String collegeId;
}
