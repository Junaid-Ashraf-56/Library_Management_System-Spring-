package model;

public class Person {
    private int libraryId;
    private String name;
    private int borrowBooks;
    private String borrowBooksName;
    private String email;

    public Person(int libraryId, String name, int borrowBooks, String borrowBooksName, String email) {
        this.libraryId = libraryId;
        this.name = name;
        this.borrowBooks = borrowBooks;
        this.borrowBooksName = borrowBooksName;
        this.email = email;
    }

    public Person() {
    }

    public int getLibraryId() {return libraryId;}
    public void setLibraryId(int libraryId) {this.libraryId = libraryId;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getBorrowBooks() {return borrowBooks;}
    public void setBorrowBooks(int borrowBooks) {this.borrowBooks = borrowBooks; }

    public String getBorrowBooksName() {return borrowBooksName;}
    public void setBorrowBooksName(String borrowBooksName) {this.borrowBooksName = borrowBooksName;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
}
