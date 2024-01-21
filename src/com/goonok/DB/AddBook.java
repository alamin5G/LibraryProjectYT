package com.goonok.DB;

import com.goonok.Interfaces.IOOperation;
import com.goonok.User.User;
import com.goonok.view.Book;

import java.util.Scanner;

public class AddBook implements IOOperation {
    @Override
    public void oper(Database database, User user) {

        Scanner input = new Scanner(System.in);
        Book book = new Book();
        System.out.println("Enter book name: ");
        String bookName = input.nextLine();
        if (database.getBook(bookName) >-1){
            System.out.println("Book already in library!");
            user.menu(database, user);
        }else {
            book.setName(bookName);
            System.out.println("Enter book author");
            book.setAuthor(input.nextLine());
            System.out.println("Enter book publisher");
            book.setPublisher(input.nextLine());
            System.out.println("Enter book collection address");
            book.setAddress(input.nextLine());
            System.out.println("Enter quantity");
            book.setQty(input.nextInt());
            System.out.println("Enter price");
            book.setPrice(input.nextDouble());
            System.out.println("Enter borrowing copies: ");
            book.setBrwcopies(input.nextInt());
            database.addBook(book);
            System.out.println("Book added successfully\n");
        }
        user.menu(database, user);

    }
}
