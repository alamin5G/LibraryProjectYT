package com.goonok.DB;

import com.goonok.User.User;
import com.goonok.library.Main;
import com.goonok.view.Book;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Database {

    private List<User> users = new ArrayList<>();
    private List<String> userNames = new ArrayList<>();
    private List<Book> bookList = new ArrayList<>();
    private List<String> bookName = new ArrayList<>();

    ///TODO - change the code from 13:00 from part-2
    private File userFile = new File(Main.class.getResource("data/Books").toExternalForm());
    private File booksFile = new File(Main.class.getResource("data/Books").toExternalForm());

    public Database(){
        if (!userFile.exists()){
            userFile.mkdirs();
        }
        if (!booksFile.exists()){
            booksFile.mkdirs();
        }
    }

    public void addUser(User user){
        users.add(user);
        userNames.add(user.getName());
    }

    public int login(String phone, String email){
        int n = -1;
        for (User user: users){
            if (user.getPhoneNumber().matches(phone) && user.getEmail().matches(email)){
                n = users.indexOf(user);
                break;
            }
        }
        return n;
    }

    public User getUser(int userIndex){
        return users.get(userIndex);
    }

    //add book
    public void addBook(Book book){
        bookList.add(book);
        bookName.add(book.getName());
    }
}
