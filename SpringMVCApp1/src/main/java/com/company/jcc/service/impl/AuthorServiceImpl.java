package com.company.jcc.service.impl;

import com.company.jcc.model.Author;
import com.company.jcc.repository.AuthorRepository;
import com.company.jcc.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.getAll();
    }

    @Override
    public Author create(Author author) {
        authorRepository.create(author);
        return author;
    }

    @Override
    public Author readById(int id) {
        return authorRepository.readById(id);
    }

    @Override
    public int readByName(String name) {
        return authorRepository.readByName(name);
    }


    @Override
    public Author update(Author author) {
        return null;
    }

    @Override
    public void delete(int id) {
        authorRepository.delete(id);
    }
}
