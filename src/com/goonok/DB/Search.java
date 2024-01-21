package com.goonok.DB;

import com.goonok.Interfaces.IOOperation;
import com.goonok.User.User;
import com.goonok.view.Book;

import java.util.Scanner;

public class Search implements IOOperation {
    @Override
    public void oper(Database database, User user) {

        System.out.println("Enter book name: ");
        Scanner input = new Scanner(System.in);
        String bookName = input.nextLine();
        int hasBook = database.getBook(bookName);
        if (hasBook>-1){
            System.out.println( database.showBook(hasBook).toStrings());
        }else {
            System.out.println("Book does not exist!");
        }
        user.menu(database, user);
    }
}
