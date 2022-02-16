package com.company.jcc.service.impl;

import com.company.jcc.model.Request;
import com.company.jcc.repository.RequestRepository;
import com.company.jcc.service.RequestService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;

    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public Request create(Request request) {
//        if (role != null) {
        return requestRepository.create(request);
//        }
    }

    @Override
    public Request readById(int id) {
        return requestRepository.readById(id);
    }

    @Override
    public Request update(Request request) {
//        if (role != null) {
        return requestRepository.update(request);
//        }
    }

    @Override
    public void delete(int id) {
        requestRepository.delete(id);
    }

    @Override
    public List<Request> getAll() {
        return requestRepository.findAll();
    }

    @Override
    public List<Request> findAllByUserId(int id) {
        return requestRepository.findAllByUserId(id);
    }

    @Override
    public long avgRequest(LocalDate start, LocalDate end) {
        return requestRepository.avgRequest(start, end);
    }


}
