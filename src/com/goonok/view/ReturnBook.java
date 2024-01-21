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
                if(b.getBook().getName().matches(bookName) &&
                b.getUser().getName().matches(user.getName())){
                    Book book = b.getBook();
                    int i = database.getAllBooks().indexOf(book);
                    if (b.getDaysLeft()<0){
                        System.out.println("You are late!" +
                                "You have to pay" +
                                 +Math.abs(b.getDaysLeft()*5)+ "as fine");
                    }
                    book.setBrwcopies(book.getBrwcopies()+1);
                    database.returnBook(b, book, i);
                    System.out.println("Book returned! Thanks");
                    break;
                }else {
                    System.out.println("You didn't borrow this book");
                }
            }
        }else {
            System.out.println("You didn't borrow this book");
        }

        user.menu(database, user);
    }
}
