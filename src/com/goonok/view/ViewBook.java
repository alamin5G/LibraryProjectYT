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
        System.out.println("Name\t\tAuthor\t\tPublisher\t\tLocation\t\tStatus\t\tQty\t\tPrice\t\tBrw Cpy");

        ///TODO - PART-4 2:33 MINS

        for( Book book : books){
            System.out.println(book.getName() + "\t\t" + book.getAuthor()+ "\t\t" + book.getPublisher()
                    + "\t\t" + book.getAddress() +  "\t\t\t" + book.getStatus() + "\t\t" + book.getQty()
                    + "\t\t" + book.getPrice() + "\t\t" + book.getBrwcopies() );
        }

        user.menu(database, user);
    }
}
