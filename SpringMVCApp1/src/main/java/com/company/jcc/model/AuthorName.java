package com.company.jcc.model;

import javax.persistence.*;

@Entity
@Table(name = "authors_name")
public class AuthorName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "coauthor_id")
    private Author coauthor;


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getCoauthor() {
        return coauthor;
    }

    public void setCoauthor(Author coauthor) {
        this.coauthor = coauthor;
    }

    public AuthorName() {
    }

    public AuthorName(Integer id, Author author, Book book, Author coauthor) {
        this.id = id;
        this.author = author;
        this.book = book;
        this.coauthor = coauthor;
    }
}
