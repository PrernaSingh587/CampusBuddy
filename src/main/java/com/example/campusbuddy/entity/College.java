package com.example.campusbuddy.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "college")
public class College implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String college;

    @OneToMany
    @Column(name="academics")
    private List<Academic> academics;

    @OneToMany
    @Column(name="events")
    private List<Event> events;

    @OneToMany
    @Column(name="places")
    private List<Place> places;

    @OneToMany
    @Column(name="miscs")
    private List<Misc> miscs;

    @OneToMany(mappedBy = "college")
    @Column(name="users")
    private List<User> users;
}
