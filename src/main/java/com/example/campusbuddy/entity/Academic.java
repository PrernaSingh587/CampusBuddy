package com.example.campusbuddy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Academic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String college;
    private String branch;
    private String subject;
    private int academicYear;

}
