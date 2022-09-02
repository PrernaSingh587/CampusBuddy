package com.example.campusbuddy.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_profile")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "firebaseId")
    private String firebaseId;
    @Column(name = "username")
    private String username;
    @Column(name = "name")
    private String name;
    @Column(name = "email_id")
    private String email;
    @Column(name = "password")
    private String password;
    @Column
    private int savedItemsId;
    @ManyToOne
    @JoinColumn
    private College college;
    @Column
    private int itemsId;
    @Column(name="date_of_birth")
    private String dob;
    @Column
    private String about;
    @Column
    private String branch;
    @Column
    private String academicYear;


}
