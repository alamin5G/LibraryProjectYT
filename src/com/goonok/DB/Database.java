package com.goonok.DB;

import com.goonok.User.User;

import java.util.ArrayList;
import java.util.List;

public class Database {

    List<User> users = new ArrayList<>();
    List<String> userNames = new ArrayList<>();

    public void addUser(User user){
        users.add(user);
        userNames.add(user.getName());
    }

    public int login(String phone, String email){
        int n = -1;
        for (User user: users){
            if (user.getPhoneNumber().matches(phone) && user.getEmail().matches(email)){
                n = users.indexOf(user);
                break;
            }
        }
        return n;
    }

    public User getUser(int userIndex){
        return users.get(userIndex);
    }
}
