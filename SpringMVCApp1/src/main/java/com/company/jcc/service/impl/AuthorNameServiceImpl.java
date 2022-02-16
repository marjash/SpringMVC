package com.company.jcc.service.impl;

import com.company.jcc.model.AuthorName;
import com.company.jcc.repository.AuthorNameRepository;
import com.company.jcc.service.AuthorNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorNameServiceImpl implements AuthorNameService {

    @Autowired
    private AuthorNameRepository authorNameRepository;

    @Override
    public void create(AuthorName authorName) {
        authorNameRepository.create(authorName);
    }

    @Override
    public List<AuthorName> readByAuthor(String surname) {
        return authorNameRepository.readByAuthor(surname);
    }

    @Override
    public AuthorName readById(int id) {
        return authorNameRepository.readById(id);
    }

    @Override
    public AuthorName update(AuthorName authorName) {
        return authorNameRepository.update(authorName);
    }

    @Override
    public void delete(int id) {
        authorNameRepository.delete(id);
    }

    @Override
    public List<AuthorName> getAll() {
        return authorNameRepository.getAll();
    }

    @Override
    public AuthorName readByTitle(String title) {
       return authorNameRepository.readByTitle(title);
    }

    @Override
    public List<AuthorName> readByCoAuthor(String surname) {
        return authorNameRepository.readByCoAuthor(surname);
    }

    public AuthorNameServiceImpl(AuthorNameRepository authorNameRepository) {
        this.authorNameRepository = authorNameRepository;
    }
}
