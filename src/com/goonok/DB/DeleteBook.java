package com.goonok.DB;

import com.goonok.Interfaces.IOOperation;
import com.goonok.User.User;

import java.util.Scanner;

public class DeleteBook implements IOOperation {
    @Override
    public void oper(Database database, User user) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter Book name: ");
        String bookName = input.nextLine();
        int i = database.getBook(bookName);
        if (i>-1){
            database.deleteBook(i);
            System.out.println("Book Deleted Successfully!");
        }else {
            System.out.println("Book not found!");
        }
        user.menu(database, user);
    }
}
