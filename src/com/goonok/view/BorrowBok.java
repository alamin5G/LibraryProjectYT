package com.goonok.view;

import com.goonok.DB.Database;
import com.goonok.Interfaces.IOOperation;
import com.goonok.User.User;

import java.util.Scanner;

public class BorrowBok implements IOOperation {
    @Override
    public void oper(Database database, User user) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter book name: ");
        String bookName = input.nextLine();
        int i = database.getBook(bookName);
        if (i>-1){
            Book book = database.showBook(i);
            boolean n = true;
            for(Borrow b : database.getBorrowList()){
                if (b.getBook().getName().matches(bookName) &&
                b.getUser().getName().matches(user.getName())){
                    n = false;
                    System.out.println("You have borrowed this book before!");
                }
            }
            if (book.getBrwcopies()>0 ){
                Borrow borrow = new Borrow(book, user);
                book.setBrwcopies(book.getBrwcopies()-1);
                database.borrowBook(borrow, book, i);
                System.out.println("You must return the book before 14 days from now\n" +
                        "Expiry Date: " + borrow.getFinish());
            }else {
                System.out.println("Book not found for borrowing");
            }

        }else {
            System.out.println("Book doesn't exist!");
        }
        user.menu(database, user);
    }
}
