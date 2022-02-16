package com.company.jcc.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "amount_left")
    private int amountLeft;

    @Column(name = "amount_gave")
    private int amountGave;

//    @OneToMany(
//            mappedBy = "book",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    private List<AuthorName> authorNames = new ArrayList<>();
//
//
//    @OneToMany(
//            mappedBy = "book",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    private List<Rent> rent = new ArrayList<>();
//
//    @ManyToMany(
//            mappedBy = "books",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    private List<Author> authors = new ArrayList<>();

    public Book() {
    }

    public Book(String bookTitle, int amountLeft, int amountGave, int rating) {
        this.bookTitle = bookTitle;
        this.amountLeft = amountLeft;
        this.amountGave = amountGave;
    }

    public int getId() {
        return id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getAmountLeft() {
        return amountLeft;
    }

    public void setAmountLeft(int amountLeft) {
        this.amountLeft = amountLeft;
    }

    public int getAmountGave() {
        return amountGave;
    }

    public void setAmountGave(int amountGave) {
        this.amountGave = amountGave;
    }


    @Override
    public String toString() {
        return "\n Book{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", amountLeft=" + amountLeft +
                ", amountGave=" + amountGave +
                '}' + "\n";
    }
}