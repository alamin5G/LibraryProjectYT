package com.goonok.view;

import com.goonok.DB.Database;
import com.goonok.Interfaces.IOOperation;
import com.goonok.User.User;

import java.util.Scanner;

public class CalculateFine implements IOOperation {
    @Override
    public void oper(Database database, User user) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book name: ");
        String bookName = scanner.nextLine();
        int i = database.getBook(bookName);

        for (Borrow b : database.getBorrowList()){
            if(b.getBook().getName().matches(bookName) && b.getUser().getName().matches(user.getName())){
                if (b.getDaysLeft()<0){
                    System.out.println("You are lte!" +
                            "You have to pay" + Math.abs(b.getDaysLeft()*5) +
                            "as fine!");
                }else {
                    System.out.println("You don't have to pay fine\n");
                }
            }
        }
        user.menu(database, user);
    }
}
