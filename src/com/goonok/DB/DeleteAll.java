package com.goonok.DB;

import com.goonok.Interfaces.IOOperation;
import com.goonok.User.User;

import java.util.Scanner;

public class DeleteAll implements IOOperation {
    @Override
    public void oper(Database database, User user) {

        System.out.println("Do you really want to delete all books? y/n:");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        choice = String.valueOf(choice.charAt(0));
        if (choice.equalsIgnoreCase("y")){
            database.deleteAllBook();
            System.out.println("All books were deleted from system.");
        }else {
            System.out.println("Your book deleted request has been cancelled!");
        }

        user.menu(database, user);

    }
}
