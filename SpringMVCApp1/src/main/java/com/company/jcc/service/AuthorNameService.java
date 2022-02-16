package com.company.jcc.service;

import com.company.jcc.model.AuthorName;
import com.company.jcc.model.Book;

import java.util.List;

public interface AuthorNameService {
    void create(AuthorName authorName);
    List<AuthorName> readByAuthor(String surname);
    AuthorName readById(int id);
    AuthorName update(AuthorName authorName);
    void delete(int id);
    List<AuthorName> getAll();
    AuthorName readByTitle(String title);
    List<AuthorName> readByCoAuthor(String surname);

}
