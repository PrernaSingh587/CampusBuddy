package com.example.campusbuddy.service;

import com.example.campusbuddy.entity.College;
import com.example.campusbuddy.entity.User;
import com.example.campusbuddy.entity.dao.UserDao;
import com.example.campusbuddy.repository.CollegeRepository;
import com.example.campusbuddy.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollegeRepository collegeRepository;


    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        if(userList.size()>0) {
            for(int i=0;i<userList.size();i++) {
                System.out.println(userList.get(i).getCollege());
            }
            return userList;
        }
            return null;
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

        if(userRepository.existsUserByEmail(email) ||
                userRepository.existsUserByUsername(username)) {
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

    public int addCollegeToUser(String college, String userid) {

        try {
            if(!userRepository.existsById(userid) || !collegeRepository.existsCollegeByCollege(college))  {
                System.out.println("Invalid Userid or College Name!");
                return  -1;
            }
            Optional<User> _user = userRepository.findById(userid);
            User user = _user.get();
            College _college =  collegeRepository.findByCollege(college);
            user.setCollege(_college);
            userRepository.save(user);

            List<User> users = _college.getUsers();
            users.add(user);
            _college.setUsers(users);
            collegeRepository.save(_college);
        } catch(Exception e) {
            System.out.println(e);
            return -1;
        }

        System.out.println("College Added for the user!");
        return 0;

    }

    public UserDao getUserById(String id) {
        Optional<User> user_ = userRepository.findById(id);
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
        return user1.getCollege()!=null ? true : false;
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
}
