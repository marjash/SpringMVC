package com.company.jcc.service;

import com.company.jcc.model.Book;
import com.company.jcc.model.Rent;


import java.util.Date;
import java.util.List;

public interface BookService {

    List<Book> getAll();
    Book create(Book book);
    Book readById(int id);
    Book update(Book book);
    void delete(int id);
    void rentBook(int id);
    List<Rent> averageTime(int id);
}
