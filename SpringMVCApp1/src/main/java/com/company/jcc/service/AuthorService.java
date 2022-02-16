package com.company.jcc.service;



import com.company.jcc.model.Author;

import java.util.List;

public interface AuthorService {
    Author create(Author author);
    Author readById(int id);
    int readByName(String name);
    Author update(Author author);
    void delete(int id);
    List<Author> getAll();
}
