package model;

import java.time.LocalDate;

public class BorrowRecord {
    private int borrowId;
    private int libraryId;
    private int isbn;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowRecord(int id, int personId, int bookId, LocalDate borrowDate, LocalDate returnDate) {
        this.borrowId = id;
        this.libraryId = personId;
        this.isbn = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public BorrowRecord(int personId, int isbn) {
        this.libraryId = personId;
        this.isbn = isbn;
    }

    public int getBorrowId() {return borrowId;}
    public void setBorrowId(int borrowId) {this.borrowId = borrowId;}

    public int getLibraryId() {return libraryId;}
    public void setLibraryId(int libraryId) {this.libraryId = libraryId;}

    public int getIsbn() {return isbn;}
    public void setIsbn(int isbn) {this.isbn = isbn;}

    public LocalDate getBorrowDate() {return borrowDate;}
    public void setBorrowDate(LocalDate borrowDate) {this.borrowDate = borrowDate;}

    public LocalDate getReturnDate() {return returnDate;}
    public void setReturnDate(LocalDate returnDate) {this.returnDate = returnDate;}
}
