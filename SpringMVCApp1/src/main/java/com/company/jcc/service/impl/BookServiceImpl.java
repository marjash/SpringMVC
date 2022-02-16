
package com.company.jcc.service.impl;

import com.company.jcc.model.Book;
//import com.company.jcc.model.Rent;
import com.company.jcc.model.Rent;
import com.company.jcc.repository.BookRepository;
import com.company.jcc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public Book create(Book book) {
        return bookRepository.create(book);
    }

    @Override
    public Book readById(int id) {
        return bookRepository.readById(id);
    }

    @Override
    public Book update(Book book) {
        return bookRepository.update(book);
    }

    @Override
    public void delete(int id) {
        bookRepository.delete(id);
    }

    @Override
    public void rentBook(int id) {
        bookRepository.rentBook(id);
    }

    @Override
    public List<Rent> averageTime(int id) {
        return bookRepository.averageTime(id);
    }
}