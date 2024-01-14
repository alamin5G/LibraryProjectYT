package com.goonok.DB;

import com.goonok.User.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Database {

    List<User> users = new ArrayList<>();
    List<String> userNames = new ArrayList<>();

    public void addUser(User user){
        users.add(user);
        userNames.add(user.getName());
    }

    public boolean login(String phone, String email){
        boolean n = false;
        for (User user: users){
            if (user.getPhoneNumber().matches(phone) && user.getEmail().matches(email)){
                n = true;
                break;
            }
        }

        return n;
    }
}
