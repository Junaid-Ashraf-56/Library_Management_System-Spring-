package model;

import java.time.LocalDate;

public class BorrowRecord {
    private int borrowId;
    private int personId;
    private int bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowRecord(int id, int personId, int bookId, LocalDate borrowDate, LocalDate returnDate) {
        this.borrowId = id;
        this.personId = personId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public BorrowRecord(int personId, int bookId) {
        this.personId = personId;
        this.bookId = bookId;
    }

    public int getBorrowId() {return borrowId;}
    public void setBorrowId(int borrowId) {this.borrowId = borrowId;}

    public int getPersonId() {return personId;}
    public void setPersonId(int personId) {this.personId = personId;}

    public int getBookId() {return bookId;}
    public void setBookId(int bookId) {this.bookId = bookId;}

    public LocalDate getBorrowDate() {return borrowDate;}
    public void setBorrowDate(LocalDate borrowDate) {this.borrowDate = borrowDate;}

    public LocalDate getReturnDate() {return returnDate;}
    public void setReturnDate(LocalDate returnDate) {this.returnDate = returnDate;}
}
