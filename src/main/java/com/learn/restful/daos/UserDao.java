package com.learn.restful.daos;

import com.learn.restful.models.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDao {
    private static List<User> users = new ArrayList<>();
    private static Integer userCounter = 2;

    static {
        users.add(new User(1, "Ahmed", new Date()));
        users.add(new User(2, "Hager", new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public User findById(Integer id){
        for (User user : users){
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public User save(User user){
        if (user.getId() == null){
            user.setId(++userCounter);
        }
        users.add(user);
        return user;
    }
}
