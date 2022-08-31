package com.example.campusbuddy.repository;

import com.example.campusbuddy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUserByUsername(String username);
    List<User>findUserByEmail(String email);
    List<User>findUserByName(String name);
    List<User>findUserByCollege(String College);
    Boolean existsUserByUsername(String username);
    Boolean existsUserByEmail(String email);

}
