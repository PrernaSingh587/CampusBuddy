package com.example.campusbuddy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TestTable")
public class TestTable {

    @Id
    private int id;
    @Column(name ="PrernaCol")
    private String name;

}
