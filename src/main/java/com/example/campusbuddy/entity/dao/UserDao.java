package com.example.campusbuddy.entity.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDao {

    private String username;

    private String name;

    private String email;

    private String password;

}
