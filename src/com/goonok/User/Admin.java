package com.goonok.User;

import com.goonok.DB.*;
import com.goonok.Interfaces.IOOperation;
import com.goonok.view.Exit;
import com.goonok.view.ViewBook;
import com.goonok.view.ViewOrder;

import java.util.Scanner;

public class Admin extends User{


    public Admin(String name) {
        super(name);
        this.operation = new IOOperation[]{
                new ViewBook(),
                new AddBook(),
                new Delete(),
                new UpdateBook(),
                new DeleteAll(),
                new Search(),
                new ViewOrder(),
                new Exit()
        };
    }

    public Admin(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
        this.operation = new IOOperation[]{
                new AddBook()
        };
    }

    @Override
    public void menu() {
        System.out.println("1. View Book List");
        System.out.println("2. Add Book List");
        System.out.println("3. Delete Book");
        System.out.println("4. Update Book");
        System.out.println("5. Remove all Book");
        System.out.println("6. Search Book");
        System.out.println("7. View Orders");
        System.out.println("8. Logout");

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        /// TODO - operations are not correct
        /// TODO - 2nd video from YouTube 3 and half minutes
        this.operation[n-1].oper();

    }
}
