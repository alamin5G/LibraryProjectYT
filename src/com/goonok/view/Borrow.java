package com.goonok.view;

import com.goonok.User.User;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Borrow {
    LocalDate start;
    LocalDate finish;
    int daysLeft;
    Book book;
    User user;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public Borrow(Book book, User user){
        start = LocalDate.now();
        finish = start.plusDays(14);
        daysLeft = Period.between(start, finish).getDays();
        this.book = book;
        this.user = user;
    }

    public Borrow(LocalDate start, LocalDate finish, Book book, User user){
        this.start = start;
        this.finish = finish;
        this.daysLeft = Period.between(start, finish).getDays();
        this.book = book;
        this.user = user;
    }

    public String getStart(){
        return dateTimeFormatter.format(start);
    }

    public String getFinish(){
        return dateTimeFormatter.format(finish);
    }

    public int getDaysLeft(){
        return Period.between(start, finish).getDays();

    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString(){
        return "Borrowing time: " + start +
                "\nExpiry date: " + finish +
                "\nDays left  : " + daysLeft;
    }

    public String toStrings(){
        return getStart() + "<N/>" + getFinish() + "<N/>" + daysLeft+
                "<N/>" + book.getName()+ "<N/>" + user.getName();
    }
}
