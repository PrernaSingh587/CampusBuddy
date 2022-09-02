package com.example.campusbuddy.repository;

import com.example.campusbuddy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{username : '?0' }")
    List<User> findUserByUsername(String username);
    @Query("{email : '?0' }")
    List<User>findUserByEmail(String email);
    @Query("{name : '?0' }")
    List<User>findUserByName(String name);
    @Query("{college.name : '?0' }")
    List<User>findUserByCollege(String College);

    @Query("{college : '?0' }")
    Boolean existsUserByUsername(String username);
    @Query("{email : '?0' }")
    Boolean existsUserByEmail(String email);

}
