package com.goonok.view;

import com.goonok.DB.Database;
import com.goonok.Interfaces.IOOperation;
import com.goonok.User.User;

import java.util.Scanner;

public class Exit implements IOOperation {
    @Override
    public void oper(Database database, User user) {
        System.out.println("Do you really want to quit?(y/n): ");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        choice = String.valueOf(choice.charAt(0));
        if (choice.equalsIgnoreCase("y")){
            database.exit();
            
        }else {
            System.out.println("Your request of quiting has been denied!");
        }

    }
}
