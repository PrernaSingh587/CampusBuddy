package com.example.campusbuddy.entity;

import com.example.campusbuddy.util.StringListConverter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@Convert(converter = StringListConverter.class)
public class College implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String college;
    private List<String> academicsId;
    private List<String> eventsId;
    private List<String> placesId;
    private List<String> miscsId;
    private List<String> usersId;
}
