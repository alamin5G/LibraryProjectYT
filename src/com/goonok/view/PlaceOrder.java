package com.goonok.view;

import com.goonok.DB.Database;
import com.goonok.Interfaces.IOOperation;
import com.goonok.User.User;

import java.util.Scanner;

public class PlaceOrder implements IOOperation {
    @Override
    public void oper(Database database, User user) {

        Order order = new Order();

        Scanner input = new Scanner(System.in);
        System.out.println("Enter book name: ");
        String bookName = input.nextLine();
        int i = database.getBook(bookName);
        if(i>-1){
            Book book = database.showBook(i);
            order.setBook(book);
            order.setUser(user);
            System.out.println("Enter Quantity: ");
            int quantity = input.nextInt();
            if (book.getQty()>=quantity){
                order.setQuantity(quantity);
                book.setQty(book.getQty()-quantity);
            }else {
                System.out.println("Your order is much bigger than our book stock!");
                System.out.println("There are " + book.getQty() + " copies of this book");
                System.out.println("Order below the mentioned size and get your copies now.");
                quantity = input.nextInt();
                order.setQuantity(quantity);
                book.setQty(book.getQty()-quantity);
            }
            order.setPrice(book.getPrice()*quantity);
            int bookPosition = database.getBook(bookName);
            database.addOrder(order, book, bookPosition);
            System.out.println("Order Placed Successfully.");
        }else {
            System.out.println("Book not found!");
        }

        user.menu(database, user);

    }
}
