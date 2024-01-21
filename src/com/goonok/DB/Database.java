package com.goonok.DB;

import com.goonok.User.Admin;
import com.goonok.User.Reader;
import com.goonok.User.User;
import com.goonok.view.Book;
import com.goonok.view.Borrow;
import com.goonok.view.Order;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Database {

    private List<User> users = new ArrayList<>();
    private List<String> userNames = new ArrayList<>();
    private List<Book> bookList = new ArrayList<>();
    private List<String> bookName = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();
    private List<Borrow> borrowList = new ArrayList<>();

    ///TODO - change the code from 13:00 from part-2

   // private File userFile = new File(Main.class.getClassLoader().getResource("data/Books").getFile());
    private final File userFile = new File("C:\\LibraryMS\\Data\\Users");
    private final File booksFile = new File("C:\\LibraryMS\\Data\\Books");
    private final File borrowFiles = new File("C:\\LibraryMS\\Data\\Borrows");
    private final File orderFiles = new File("C:\\LibraryMS\\Data\\Orders");

    public Database() {
        File folder = new File("C:\\LibraryMS\\Data");
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
        if (!orderFiles.exists()){
            try {
                orderFiles.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        if (!borrowFiles.exists()){
            try{
                borrowFiles.createNewFile();
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }

        getUsers();
        getBooks();
        getOrders();
        getBorrows();
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
        saveBooks();
    }

    private void getUsers(){
        StringBuilder text1 = new StringBuilder();
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(userFile));
            String s1;
            while ((s1 = br1.readLine()) != null){
                text1.append(s1);
            }
            br1.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!text1.toString().matches("" ) || (!text1.isEmpty())){
            String[] a1 = text1.toString().split("<NewUser/>");
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
        StringBuilder text1 = new StringBuilder();
        for(User user : users){
            text1.append(user.toString()).append("<NewUser/>\n");
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
        StringBuilder text1 = new StringBuilder();
        for(Book book : bookList){
            text1.append(book.toStrings()).append("<NewBook/>\n");
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
        StringBuilder text1 = new StringBuilder();
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(booksFile));
            String s1;
            while ((s1 = br1.readLine()) != null){
                text1.append(s1);
            }
            br1.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!text1.toString().matches("" ) || (!text1.isEmpty())){
            String[] a1 = text1.toString().split("<NewBook/>");
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
        book.setPrice(Double.parseDouble(a[5]));
        book.setBrwcopies(Integer.parseInt(a[6]));
        return book;
    }

    ///TODO - PART-4 1:35 MIN
    public List<Book> getAllBooks()
    {
        return bookList;
    }

    public int getBook(String bookName){
        int i = -1;
        for (Book book : bookList){
            if (book.getName().matches(bookName)){
                i  = bookList.indexOf(book);
            }
        }
        return i;
    }

    public void deleteBook(int position){
        bookList.remove(position);
        bookName.remove(position);
        saveBooks();
    }

    public Book showBook(int position){
        return bookList.get(position);
    }

    public void deleteAllBook(){
        if (booksFile.exists()){
            try{
                booksFile.delete();
            }catch (Exception e){
                throw new RuntimeException();
            }
        }
    }

    public void exit(){
        System.exit(0);
    }

    public void addOrder(Order order, Book book, int bookPosition){
        orderList.add(order);
        bookList.set(bookPosition, book);
        saveOrders();
        saveBooks();
    }

    public void saveOrders(){
        StringBuilder text1 = new StringBuilder();
        for(Order order : orderList){
            text1.append(order.toStrings()).append("<NewOrder/>\n");
        }
        try{
            PrintWriter pw = new PrintWriter(orderFiles);
            pw.print(text1);
            pw.close();
            System.err.println("Order Saved");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void getOrders(){
        StringBuilder text1 = new StringBuilder();
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(orderFiles));
            String s1;
            while ((s1 = br1.readLine()) != null){
                text1.append(s1);
            }
            br1.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!text1.toString().matches("" ) || (!text1.isEmpty())){
            String[] a1 = text1.toString().split("<NewOrder/>");
            for (String s : a1){
                Order order = parseOrder(s);
                orderList.add(order);
            }

        }
    }

    public User getUserByName(String userName){
        User u = new Reader("");
        for(User user : users){
            if(user.getName().matches(userName)){
                u = user;
            }
        }
        return u;
    }

    public Order parseOrder(String s){
        String[] a = s.split("<N/>");

        return new Order(bookList.get(getBook(a[0])), getUserByName(a[1]),
                Double.parseDouble(a[2]), Integer.parseInt(a[3]));
    }

    public List<Order> getAllOrder(){
        return orderList;
    }


    public void saveBorrows(){
        StringBuilder text1 = new StringBuilder();
        for(Borrow borrow : borrowList){
            text1.append(borrow.toStrings()).append("<NewBorrow/>\n");
        }
        try{
            PrintWriter pw = new PrintWriter(borrowFiles);
            pw.print(text1);
            pw.close();
            System.err.println("Borrow Saved");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public void getBorrows(){
        StringBuilder text1 = new StringBuilder();
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(borrowFiles));
            String s1;
            while ((s1 = br1.readLine()) != null){
                text1.append(s1);
            }
            br1.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!text1.toString().matches("" ) || (!text1.isEmpty())){
            String[] a1 = text1.toString().split("<NewBorrow/>");
            for (String s : a1){
                Borrow borrow = parseBorrow(s);
                borrowList.add(borrow);
            }

        }
    }

    private Borrow parseBorrow(String s) {
        String[] a = s.split("<N/>");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate start = LocalDate.parse(a[0], formatter);
        LocalDate finish = LocalDate.parse(a[1], formatter);
        Book book = showBook(getBook(a[3]));
        User user = getUserByName(a[4]);
        return new Borrow(start, finish, book, user);
    }

    public void borrowBook(Borrow borrow, Book book, int bookPosition) {
        borrowList.add(borrow);
        bookList.set(bookPosition, book);
        saveBorrows();
        saveBooks();

    }

    public List<Borrow> getBorrowList(){
        return borrowList;
    }

    public void returnBook(Borrow b, Book book, int i) {
        ///TODO - PART-6 13:00
    }
}
