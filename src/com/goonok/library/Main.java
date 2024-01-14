package com.goonok.library;

import com.goonok.DB.Database;
import com.goonok.User.Admin;
import com.goonok.User.Reader;
import com.goonok.User.User;

import java.util.Scanner;

public class Main {
    static Scanner input ;
    public static void main(String[] args) {

        Database database = new Database();

        input = new Scanner(System.in);
        System.out.println("Welcome to Library Management System!");
        System.out.println("1. Login\n2.New User");

        int n = input.nextInt();
        switch (n){
            case 1: login();
            case 2: newUser();
            default:
                System.out.println("Invalid choice");
        }
    }

    private static void login(){
        System.out.println("Enter PhoneNumber: ");
        String phoneNumber = input.next();
        System.out.println("Enter email");
        String email = input.next();
    }

    private static void newUser(){
        System.out.println("Enter name");
        String name = input.next();
        System.out.println("Enter PhoneNumber: ");
        String phoneNumber = input.next();
        System.out.println("Enter email");
        String email = input.next();
        System.out.println("1. Admin\n2. Reader");
        int n2 = input.nextInt();
        if (n2==1){
            User admin = new Admin(name, email, phoneNumber);

        }else if(n2==2){
            User reader = new Reader(name, email, phoneNumber);

        }else {
            System.out.println("No Valid choice");
        }
    }

}