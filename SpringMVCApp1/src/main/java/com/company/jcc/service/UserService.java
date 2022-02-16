package com.company.jcc.service;

import com.company.jcc.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User create(User user);
    User readById(int id);
    User update(User user);
    void delete(int id);
    List<User> getAll();
    User findByEmail(String email);
    int avgAge();

}
