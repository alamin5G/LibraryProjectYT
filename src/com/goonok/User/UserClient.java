package com.goonok.User;

import com.goonok.DB.Database;

import java.util.Scanner;

public class UserClient {

    private final Database database = new Database();

    public void login(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter PhoneNumber: ");
        String phoneNumber = in.next();
        System.out.println("Enter email");
        String email = in.next();
        int userIndex = database.login(phoneNumber, email);
        if (userIndex != -1){
            User user = database.getUser(userIndex);
            System.out.println(user.getName()+" Login Success!");
            user.menu(database, user);
        }else {
            System.out.println("Login Failed!");
        }

    }

    public void newUser(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter name");
        String name = in.next();
        if(!database.userExists(name)){
            System.out.println("Enter PhoneNumber: ");
            String phoneNumber = in.next();
            System.out.println("Enter email");
            String email = in.next();
            System.out.println("1. Admin\n2. Reader");
            int n2 = in.nextInt();
            if (n2==1){
                User admin = new Admin(name, email, phoneNumber);
                database.addUser(admin);
                System.out.println("New Librarian Account Created");
                admin.menu(database, admin);
            }else if(n2==2){
                User reader = new Reader(name, email, phoneNumber);
                database.addUser(reader);
                System.out.println("New Reader Account Created");
                reader.menu(database, reader);

            }else {
                System.out.println("No Valid choice");
            }

        }else {
            System.out.println("User already exist!");
            return;
        }

    }

}
