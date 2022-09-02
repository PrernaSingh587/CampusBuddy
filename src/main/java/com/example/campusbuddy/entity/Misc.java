package com.example.campusbuddy.entity;

import com.example.campusbuddy.util.StringListConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Misc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String content;
    private User user;
    private Date date;
    private int likes;
    @Convert(converter = StringListConverter.class)
    private List<String> comments; // cant add list of strings like this, need to convert it to object.
}
