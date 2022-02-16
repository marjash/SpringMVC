package com.company.jcc.service.impl;

import com.company.jcc.model.Rent;
import com.company.jcc.model.User;
import com.company.jcc.repository.RentRepository;
import com.company.jcc.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentServiceImpl implements RentService {
    @Autowired
    private final RentRepository rentRepository;

    public RentServiceImpl(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    @Override
    public Rent create(Rent rent) {
        return rentRepository.create(rent);
    }

    @Override
    public Rent readById(int id) {
        return rentRepository.readById(id);
    }
    @Override
    public Rent update(Rent rent) {
        return rentRepository.update(rent);
    }

    @Override
    public void delete(int id) {
        rentRepository.delete(id);
    }

    @Override
    public List<Rent> getAll() {
        return rentRepository.findAll();
    }

    @Override
    public Rent getMostPopular(LocalDate start, LocalDate end) {
        return rentRepository.getMostPopular(start, end);
    }

    @Override
    public Rent getMostUnpopular(LocalDate start, LocalDate end) {
        return rentRepository.getMostUnPopular(start, end);
    }

    @Override
    public List<User> findUsersNotReturnedInTime() {
        return rentRepository.findUsersNotReturnedInTime();
    }

    @Override
    public int howManyBook(LocalDate start, LocalDate end) {
        return rentRepository.howManyBook(start, end);
    }

    @Override
    public List<Rent> hasRead(int id) {
        return rentRepository.hasRead(id);
    }

    @Override
    public void backBook(int id) {
        rentRepository.backBook(id);
    }

}
