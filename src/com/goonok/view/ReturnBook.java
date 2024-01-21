package com.goonok.view;

import com.goonok.DB.Database;
import com.goonok.Interfaces.IOOperation;
import com.goonok.User.User;

import java.util.Scanner;

public class ReturnBook implements IOOperation {
    @Override
    public void oper(Database database, User user) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter book name");
        String bookName = input.nextLine();
        if(!database.getBorrowList().isEmpty()){
            for(Borrow b : database.getBorrowList()){
                ///TODO PART-6 11:57 minutes
            }
        }
    }
}
