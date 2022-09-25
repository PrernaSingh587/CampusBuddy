package com.example.campusbuddy.controller;

import com.example.campusbuddy.entity.dao.UserDao;
import com.example.campusbuddy.service.UserService;
import com.example.campusbuddy.entity.User;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

   @Autowired
   private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //done
    @GetMapping(value = "/list-users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> listUsers() {
        List<User> userList = userService.findAll();
        if(userList.size() == 0) {
            System.out.println("Not Present!");
        }
        return userList;
    }


    @GetMapping(value = "/list-users-status/{collegeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String,String>> listUsersOnlineStatus(@PathVariable String collegeId) {

        return userService.listUsersOnlineStatus(collegeId);
    }

    //done
    @PostMapping(value = "/save-user")
    public String saveUser(@RequestBody UserDao user) {
        String res;
        try {
           res = userService.save(user);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        if(res!= null)
        System.out.println("User Saving Success");
        else  System.out.println("User Saving Failed");
        return res;
    }

    //done
    @PostMapping(value = "/add-college")
    public int addCollege(@RequestBody Map<String, String> requestBody) {
        String collegeid = requestBody.get("collegeid");
        String userid =  requestBody.get("userid");
         System.out.println(collegeid);
         System.out.println(userid);
        int res;
        try {
            res = userService.addCollegeToUser(collegeid,userid);
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
        if(res == 0)
            System.out.println("Saving Success");
        else  System.out.println("Saving NOT Success");
        return res;
    }

    //done
    @GetMapping(value =  "/user-id/{id}")
    public UserDao getUserById(@PathVariable String id) {
        UserDao userDao = userService.getUserById(id);
        return userDao;
    }

    //done
    @GetMapping(value = "/user-email/{email}")
    public String getUserByEmail(@PathVariable String email) {

        User user = userService.getUserByEmail(email);
        if(user == null) {
            System.out.println("No User Found!");
            return null;
        }
        return user.getId();
    }

    @GetMapping(value = "/user-name/{username}")
    public String getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if(user == null) {
            System.out.println("No User Found!");
            return null;
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

    @PostMapping(value = "/save-user-data")
    public int saveUserData(@RequestBody User user) {
        return  userService.saveUserData(user);
    }

    //done
    @GetMapping(value = "/user-has-college/{id}")
    public Boolean userHasCollege(@PathVariable String id) {
        return userService.userHasCollege(id);
    }

    @PostMapping(value = "/get-user-college")
    public Map<String,String> getUserCollege(@RequestBody Map<String,String> userid) {
        return userService.getUserCollege(userid.get("userid"));
    }

    @PostMapping(value = "/toggle-online")
    public int toggleOnline(@RequestBody Map<String,String> requestBody) {
        return userService.toggleOnline(requestBody);
    }

}
