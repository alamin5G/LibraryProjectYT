package com.goonok.library;
import com.goonok.User.UserClient;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        System.out.println("Welcome to Library Management System!");
        UserClient client = new UserClient();
        Scanner input = new Scanner(System.in);

        while (true){
            System.out.println("0. Exit \n1. Login\n2.New User");
            int n = input.nextInt();
            switch (n){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    client.login();
                    break;
                case 2:
                    client.newUser();
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }
}