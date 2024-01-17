package com.goonok.DB;

import com.goonok.User.Admin;
import com.goonok.User.Reader;
import com.goonok.User.User;
import com.goonok.library.Main;
import com.goonok.view.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Database {

    private List<User> users = new ArrayList<>();
    private List<String> userNames = new ArrayList<>();
    private List<Book> bookList = new ArrayList<>();
    private List<String> bookName = new ArrayList<>();

    ///TODO - change the code from 13:00 from part-2

   // private File userFile = new File(Main.class.getClassLoader().getResource("data/Books").getFile());
    private File userFile = new File("C:\\LibraryMS\\Data\\Users");
    private File booksFile = new File("C:\\LibraryMS\\Data\\Books");
    private File folder = new File("C:\\LibraryMS\\Data");
    public Database(){
        if (!folder.exists()){
            folder.mkdirs();
        }
        if (!userFile.exists()){
            try {
                userFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (!booksFile.exists()){
            try {
                booksFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        getUsers();
    }

    public void addUser(User user){
        users.add(user);
        userNames.add(user.getName());
        saveUsers();
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

    private void getUsers(){
        String text1 = "";
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(userFile));
            String s1;
            while ((s1 = br1.readLine()) != null){
                text1 =  text1 + s1;
            }
            br1.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!text1.matches("" ) || !text1.isEmpty()){
            String[] a1 = text1.split("<NewUser/>");
            for (String s : a1){
                String[] a2 = s.split("<N/>");
                if (a2[3].matches("Admin")){
                    User user = new Admin(a2[0], a2[1], a2[2] );
                    addUser(user);
                }else {
                    User user = new Reader(a2[0], a2[1], a2[2]);
                    addUser(user);
                }
            }

        }
    }


    ///TODO - 3rd video 2:08 sec
    private void saveUsers(){
        String text1 = "";
        for(User user : users){
            text1 = text1 + user.toString() + "<NewUser/>\n";
        }
        try{
            PrintWriter pw = new PrintWriter(userFile);
            pw.print(text1);
            pw.close();
            System.err.println("Data Saved");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveBooks(){
        String text1 = "";
        for(Book book : bookList){
            text1 = text1 + book.toString() + "<NewBook/>\n";
        }
        try{
            PrintWriter pw = new PrintWriter(booksFile);
            pw.print(text1);
            pw.close();
            System.err.println("Book Saved");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private void getBooks(){
        String text1 = "";
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(booksFile));
            String s1;
            while ((s1 = br1.readLine()) != null){
                text1 =  text1 + s1;
            }
            br1.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!text1.matches("" ) || !text1.isEmpty()){
            String[] a1 = text1.split("<NewBook/>");
            for (String s : a1){
                Book book = parseBook(s);
                bookList.add(book);
                bookName.add(book.getName());
            }

        }
    }

    public Book parseBook(String s){
        String[] a = s.split("<N/>");
        Book book = new Book();
        book.setName(a[0]);
        book.setAuthor(a[1]);
        book.setPublisher(a[2]);
        book.setAddress(a[3]);
        book.setQty(Integer.parseInt(a[4]));
        book.setPrice(Integer.parseInt(a[5]));
        book.setBrwcopies(Integer.parseInt(a[6]));
        return book;
    }

}
