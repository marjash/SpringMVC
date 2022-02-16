package com.company.jcc.model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "rent")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "time_taken")
    private LocalDate timeTaken;

    @Basic
    @Column(name = "time_should_be_returned")
    private LocalDate timeShouldBeReturned;

    @Basic
    @Column(name = "time_returned")
    private LocalDate timeReturned;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Rent() {
    }

    public Rent(User user, Book book, LocalDate timeTaken, LocalDate timeShouldBeReturned) {
        this.user = user;
        this.book = book;
        this.timeTaken = timeTaken;
        this.timeShouldBeReturned = timeShouldBeReturned;
    }

    public LocalDate getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(LocalDate timeTaken) {
        this.timeTaken = timeTaken;
    }

    public LocalDate getTimeShouldBeReturned() {
        return timeShouldBeReturned;
    }

    public void setTimeShouldBeReturned(LocalDate timeShouldBeReturned) {
        this.timeShouldBeReturned = timeShouldBeReturned;
    }

    public LocalDate getTimeReturned() {
        return timeReturned;
    }

    public void setTimeReturned(LocalDate timeReturned) {
        this.timeReturned = timeReturned;
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
        return "\n Rent{" +
                "id=" + id +
                ", timeTaken=" + timeTaken +
                ", timeShouldBeReturned=" + timeShouldBeReturned +
                ", timeReturned=" + timeReturned +
                '}' + "\n";
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rent rent = (Rent) o;
        return id == rent.id && timeTaken.equals(rent.timeTaken) && timeShouldBeReturned.equals(rent.timeShouldBeReturned) && Objects.equals(timeReturned, rent.timeReturned) && user.equals(rent.user) && book.equals(rent.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeTaken, timeShouldBeReturned, timeReturned, user, book);
    }
}