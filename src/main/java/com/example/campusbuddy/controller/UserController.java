package com.example.campusbuddy.controller;

import com.example.campusbuddy.entity.TestTable;
import com.example.campusbuddy.entity.dao.UserDao;
import com.example.campusbuddy.repository.TestRepository1;
import com.example.campusbuddy.service.UserService;
import com.example.campusbuddy.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:8089")
@RestController
@RequestMapping(value = "/user")
public class UserController {

   @Autowired
   private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/list-users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> listUsers() {
        return userService.findAll();
    }

    @PostMapping(value = "/save-user")
    public Long saveUser(@RequestBody UserDao user) {
        Long res;
        try {
           res = userService.save(user);
        } catch (Exception e) {
            System.out.println(e);
            return -1L;
        }
        if(res > 0)
        System.out.println("Saving Success");
        else  System.out.println("Saving NOT Success");
        return res;
    }

    @PostMapping(value = "/add-college/{userid}/{college}")
    public int addCollege(@PathVariable String college, @PathVariable Long userid) {
        int res;
        try {
            res = userService.addCollegeToUser(college,userid);
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
        if(res == 0)
            System.out.println("Saving Success");
        else  System.out.println("Saving NOT Success");
        return res;
    }

    @GetMapping(value =  "/user-id/{id}")
    public UserDao getUserById(@PathVariable Long id) {
        System.out.println("getuserbyid");
        return userService.getUserById(id);
    }

    @GetMapping(value = "/user-email/{email}")
    public Long getUserByEmail(@PathVariable String email) {

        User user = userService.getUserByEmail(email);
        if(user == null) {
            System.out.println("No User Found!");
            return -1L;
        }
        return user.getId();
    }

    @GetMapping(value = "/user-name/{username}")
    public Long getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if(user == null) {
            System.out.println("No User Found!");
            return -1L;
        }
        return user.getId();
    }

//    @PostMapping(value = "/firebase-id-save/{userid}/{firebaseid}")
//    public User saveFirebaseId(@PathVariable Long userid,
//                               @PathVariable Long firebaseid) {
//        User user = userService.saveUserFirebaseId(userid,firebaseid);
//        if(user == null) {
//            System.out.println("No User Found!");
//        }
//        return user;
//    }

    @PostMapping(value = "/save-user-data/")
    public int saveUserData(@RequestBody User user) {
        return  userService.saveUserData(user);
    }

    @GetMapping(value = "/user-has-college/{id}")
    public Boolean userHasCollege(@PathVariable Long id) {
        System.out.println("inside");
        return userService.userHasCollege(id);
    }

    @Autowired
    TestRepository1 testRepository1;

    @PostMapping(value = "/addSome")
    public String p(@RequestBody TestTable testTable) {
        testRepository1.save(testTable);
        return "Succeeded!";
    }


}
