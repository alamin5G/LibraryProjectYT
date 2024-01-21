package com.goonok.view;

import com.goonok.DB.Database;
import com.goonok.Interfaces.IOOperation;
import com.goonok.User.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewOrder implements IOOperation {
    @Override
    public void oper(Database database, User user) {

        System.out.println("Enter book name: ");
        Scanner input = new Scanner(System.in);
        String bookName = input.nextLine();

        int i = database.getBook(bookName);
        if(i>-1){

            System.out.println("Book\t\tUser\t\tQty\t\tPrice");
            for(Order order : database.getAllOrder()){
                if (order.getBook().getName().matches(bookName)){
                    System.out.println(order.getBook().getName()+"\t\t" +
                            order.getUser().getName() + "\t\t" +
                            order.getQuantity() + "\t\t" +
                            order.getPrice());
                }
            }


        }else {
            System.out.println("Order not found by book name!");
            System.out.println("or the book name you have entered was wrong");
        }

        user.menu(database, user);
    }
}
