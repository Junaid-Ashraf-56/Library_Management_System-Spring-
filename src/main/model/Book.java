package main.model;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private int isbn;
    private boolean available;

    public Book(int bookId, String title, String author, int isbn, boolean available) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = available;
    }

    public int getBookId() {return bookId;}
    public void setBookId(int bookId) {this.bookId = bookId;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}

    public int getIsbn() {return isbn;}
    public void setIsbn(int isbn) {this.isbn = isbn;}

    public boolean isAvailable() {return available;}
    public void setAvailable(boolean available) {this.available = available;}
}
