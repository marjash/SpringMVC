package com.company.jcc.model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Basic
    @Column
    private LocalDate time;

    @ManyToOne
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
    @JoinColumn(name = "book_id")
    private Book book;

    public Request() {
    }

    public Request(LocalDate time, User user, Book book) {
        this.time = time;
        this.user = user;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "\n Request{" +
                "id=" + id +
                ", time=" + time +
                '}' + "\n";
    }
}
