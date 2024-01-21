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
        boolean done = true;
        int i = database.getBook(bookName);

        for (Borrow b : database.getBorrowList()){
            if(b.getBook().getName().matches(bookName) && b.getUser().getName().matches(user.getName())){
                if (b.getDaysLeft()<0){
                    System.out.println("You are late!" +
                            "You have to pay" + b.getDaysLeft()*5 +
                            "as fine!");
                }else {
                    System.out.println("You don't have to pay fine\n");
                }
                done = false;
                break;
            }
            if (done){
                System.out.println("You didn't borrow this book!");
            }
        }
        user.menu(database, user);
    }
}
