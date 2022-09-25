package com.example.campusbuddy.service;

import com.example.campusbuddy.entity.College;
import com.example.campusbuddy.entity.User;
import com.example.campusbuddy.entity.dao.UserDao;
import com.example.campusbuddy.repository.CollegeRepository;
import com.example.campusbuddy.repository.UserRepository;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollegeRepository collegeRepository;


    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        System.out.println(userList.size());
        if(userList.size()>0) {
            for(int i=0;i<userList.size();i++) {
                System.out.println(userList.get(i).getCollegeid());
            }
            return userList;
        }
            return null;
    }


    public List<Map<String,String>> listUsersOnlineStatus(String collegeId) {
        try {
            Optional<College> college_ = collegeRepository.findById(collegeId);
            College college = college_.get();
            List<String> userListIds = college.getUsersId();
            List<Map<String,String>>userOnlineStatus = new ArrayList<Map<String,String>>();

            if(userListIds!=null && userListIds.size()>0) {
                for(int i=0;i<userListIds.size();i++) {
                    Map<String,String>mp = new HashMap<>();
                    String userid = userListIds.get(i);

                    Optional<User> user_ = userRepository.findById(userid);
                    User user = user_.get();

                    mp.put("userid", user.getId());
                    mp.put("name", user.getName());
                    mp.put("username", user.getUsername());
                    if(user.getIsOnline()!= null && user.getIsOnline()==true)
                    mp.put("isOnline", "true");
                    else  mp.put("isOnline", "false");

                    userOnlineStatus.add(mp);
                }
                return userOnlineStatus;
            }
            else return null;
        } catch (Exception e) {
            System.out.println(e + "Cant fetch online status");
            return null;
        }

    }

    public String save(UserDao userdao) {
        String email = userdao.getEmail();
        String name = userdao.getName();
        String username = userdao.getUsername();
        String password = userdao.getPassword();
        System.out.println(email + " " + username);

//        String firebaseId = userdao.getFirebaseId();
//        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseId);
//        String uid = decodedToken.getUid();

        if(userRepository.findUserByEmail(email).size()!=0 ||
                userRepository.findUserByUsername(username).size()!=0) {
            System.out.println("Already Present!");
            return null;
        }

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setUsername(username);

        userRepository.save(user);
        List<User> _users= userRepository.findUserByUsername(username);
        if(_users.size() == 0 ) {
            System.out.println("No User Found!");
            return null;
        }
        String userId = _users.get(0).getId();
        System.out.println(userId);
        return userId;
    }

    public int addCollegeToUser(String collegeid, String userid) {

        try {
            Optional<User> user_ = userRepository.findById(userid);
            User user = user_.get();
            Optional<College> college_ = collegeRepository.findById(collegeid);
            College college = college_.get();

            if(user == null || college == null)  {
                System.out.println("Invalid Userid or College Name!");
                return  -1;
            }

            user.setCollegeid(collegeid);
            userRepository.save(user);

            List<String> userList_ = college.getUsersId();
            if(userList_ == null) {
                userList_ = new ArrayList<>();
            }
            userList_.add(user.getId());
            college.setUsersId(userList_);
            collegeRepository.save(college);
        } catch(Exception e) {
            System.out.println(e);
            return -1;
        }

        System.out.println("College Added for the user!");
        return 0;

    }

    public UserDao getUserById(String id) {
        System.out.println(id);
        Optional<User> user_ = userRepository.findById(id);
        if(user_.isEmpty()) {
            return null;
        }
        User user =  user_.get();
        String username = user.getUsername();
        String name = user.getName();
        String email = user.getEmail();

        UserDao userDao = new UserDao();
        userDao.setUsername(username);
        userDao.setEmail(email);
        userDao.setName(name);
        userDao.setPassword("");
        return userDao;
    }

    public User getUserByEmail(String email) {
        List<User> user = userRepository.findUserByEmail(email);
        if(user.size()==0) {
            return null;
        }
        return user.get(0);
    }

    public  User getUserByUsername(String username) {
        List<User> user = userRepository.findUserByUsername(username);
        if(user.size()==0) {
            return null;
        }
        return user.get(0);
    }

    public Boolean userHasCollege(String userid) {
        Optional<User> _user1 = userRepository.findById(userid);
        if(_user1.isEmpty()) return false;
        User user1 = _user1.get();
        return user1.getCollegeid()!=null ? true : false;
    }

    public int saveUserData(User user) {
        String email = user.getEmail();
        String username = user.getUsername();
        String id = user.getId();
        if(!userRepository.existsById(id)) {
            System.out.println("Invalid User, cant save!");
            return -1;
        }
        if(!userRepository.existsUserByEmail(email) ||
                !userRepository.existsUserByUsername(username)) {
            System.out.println("Invalid User, cant save!");
            return -1;
        }
        Optional<User> _user1 = userRepository.findById(id);
        User user1 = _user1.get();
        if(user1.getEmail()!=email || user1.getUsername()!=username) {
            System.out.println("Invalid User, cant save!");
            return -1;
        }
        userRepository.save(user);
        return 0;
    }

    public Map<String,String> getUserCollege(String userid) {
        try {
            Optional<User> user_ = userRepository.findById(userid);
            if(user_.isEmpty()) return null;
            User user = user_.get();
            System.out.println(user.getUsername());
            if(user.getCollegeid() == null) return null;

            String collegeId = user.getCollegeid();
            Optional<College> college_ = collegeRepository.findById(collegeId);

            Map<String,String> mp = new HashMap<String,String>();
            mp.put("label", college_.get().getCollege());
            mp.put("value", college_.get().getId());
            return mp;
        } catch(Exception e) {
            System.out.println("Error in fetching college for user");
            return null;
        }
    }

    public int toggleOnline(Map<String, String> requestBody) {
        String userid = requestBody.get("userid");
        try {
            Optional<User> user_ = userRepository.findById(userid);
            if(user_.isEmpty()) return -1;
            User user = user_.get();

            Boolean isOnlineStatus = user.getIsOnline();
            if(isOnlineStatus==null || isOnlineStatus==false){
                user.setIsOnline(true);
                userRepository.save(user);
                System.out.println("User just logged in! " + user.getUsername());
                return 1;
            }
            if(isOnlineStatus == true) {
                user.setIsOnline(false);
                userRepository.save(user);
                System.out.println("User just logged out! " + user.getUsername());
                return 0;
            }
            return 1;

        } catch (Exception e) {
            System.out.println("Error in toggling online status for user");
            return -1;
        }
    }

}
