package com.goonok.view;

import com.goonok.DB.Database;
import com.goonok.Interfaces.IOOperation;
import com.goonok.User.User;

import java.util.ArrayList;
import java.util.List;

public class ViewBook implements IOOperation {
    @Override
    public void oper(Database database, User user) {

        List<Book> books = database.getAllBooks();
        System.out.println("Name\tAuthor\tPublisher\tCollection Location\tStatus\tQty\tPrice\tBorrowing Copies");

        ///TODO - PART-4 2:33 MINS

        for( Book book : books){
            System.out.println(book.getName() + "\t" + book.getAuthor()+ "\t" + book.getPublisher()
                    + "\t" + book.getAddress() +  "\t" + book.getStatus() + "\t" + book.getQty()
                    + "\t" + book.getPrice() + "\t" + book.getBrwcopies() );
        }
    }
}
