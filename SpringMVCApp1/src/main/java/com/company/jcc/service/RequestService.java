package com.company.jcc.service;

import com.company.jcc.model.Request;

import java.time.LocalDate;
import java.util.List;

public interface RequestService {
    Request create(Request request);
    Request readById(int id);
    Request update(Request request);
    void delete(int id);
    List<Request> getAll();
    List<Request> findAllByUserId(int id);
    long avgRequest(LocalDate start, LocalDate end);
}
