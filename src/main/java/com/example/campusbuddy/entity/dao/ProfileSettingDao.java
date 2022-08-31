package com.example.campusbuddy.entity.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileSettingDao {


    private String name;
    private String dob;
    private String about;

}
