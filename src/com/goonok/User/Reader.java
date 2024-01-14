package com.goonok.User;

public class Reader extends User{
    public Reader(String name) {
        super(name);
    }

    public Reader(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
    }

    @Override
    public void menu() {
        System.out.println("1. Search");
        System.out.println("2. Place Order");
        System.out.println("3. Place Order");
        System.out.println("4. Borrow Book");
        System.out.println("5. Calculate Fine");
        System.out.println("6. Return Book");
        System.out.println("7. View Books");
        System.out.println("8. Logout");
    }
}
