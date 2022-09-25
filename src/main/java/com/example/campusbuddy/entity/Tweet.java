package com.example.campusbuddy.entity;

import com.example.campusbuddy.util.StringListConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@Convert(converter = StringListConverter.class)
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String content;
    private String userid;
    private String date;
    private Set<String> likes; //contains userids of "likers"
    private List<Map<String,String>> comments; // cant add list of strings like this, need to convert it to object.
}
