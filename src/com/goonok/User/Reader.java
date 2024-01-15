package com.goonok.User;

import com.goonok.DB.Database;
import com.goonok.DB.Search;
import com.goonok.Interfaces.IOOperation;
import com.goonok.view.*;

import java.util.Scanner;

public class Reader extends User{
    public Reader(String name) {
        super(name);
        this.operation = new IOOperation[]{
                new Search(),
                new ViewBook(),
                new PlaceOrder(),
                new BorrowBok(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit()

        };
    }

    public Reader(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
        this.operation = new IOOperation[]{
                new Search(),
                new ViewBook(),
                new PlaceOrder(),
                new BorrowBok(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit()

        };
    }

    @Override
    public void menu(Database database, User user) {
        System.out.println("1. Search");
        System.out.println("2. View Books");
        System.out.println("3. Place Order");
        System.out.println("4. Borrow Book");
        System.out.println("5. Calculate Fine");
        System.out.println("6. Return Book");
        System.out.println("7. Logout");

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        this.operation[n-1].oper(database, user);
    }
}
