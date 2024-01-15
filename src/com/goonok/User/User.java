package com.goonok.User;

import com.goonok.DB.Database;
import com.goonok.Interfaces.IOOperation;

public abstract class User {
    protected String name;
    protected String email;
    protected String phoneNumber;

    protected IOOperation[] operation;

    public User(String name){
        this.name = name;
    }

    public User(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    abstract public void menu(Database database, User user);
}
