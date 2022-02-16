package com.company.jcc.service;

import com.company.jcc.model.Rent;
import com.company.jcc.model.User;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface RentService {
    Rent create(Rent rent);
    Rent readById(int id);
    Rent update(Rent rent);
    void delete(int id);
    List<Rent> getAll();
    Rent getMostPopular(LocalDate start, LocalDate end);
    Rent getMostUnpopular(LocalDate start, LocalDate end);
    List<User> findUsersNotReturnedInTime();
    int howManyBook(LocalDate start, LocalDate end);
    List<Rent> hasRead(int id);
    void backBook(int id);

}
