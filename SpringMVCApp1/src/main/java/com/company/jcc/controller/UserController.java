package com.company.jcc.controller;

import com.company.jcc.model.Book;
import com.company.jcc.model.Rent;
import com.company.jcc.model.Request;
import com.company.jcc.model.User;
import com.company.jcc.config.EmailService;
import com.company.jcc.service.impl.BookServiceImpl;
import com.company.jcc.service.impl.RentServiceImpl;
import com.company.jcc.service.impl.RequestServiceImpl;
import com.company.jcc.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserServiceImpl userService;

    @Autowired
    private final RentServiceImpl rentService;

    @Autowired
    private final RequestServiceImpl requestService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final BookServiceImpl bookService;


    public UserController(UserServiceImpl userService, RentServiceImpl rentService, RequestServiceImpl requestService, PasswordEncoder passwordEncoder, BookServiceImpl bookService) {
        this.userService = userService;
        this.rentService = rentService;
        this.requestService = requestService;
        this.passwordEncoder = passwordEncoder;
        this.bookService = bookService;
    }

    @GetMapping("/{id}/create/request")
    public String createRequest(@PathVariable int id, Model model) {
        model.addAttribute("request", new Request());
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("user", userService.readById(id));
        return "create_request";
    }

    @PostMapping("/{id}/create/request")
    public String create(@Validated @PathVariable("id") int id, @ModelAttribute Request request,
                         @RequestParam("bookId") int bookId, Model model){
        Book book = bookService.readById(bookId);
        User user = userService.readById(id);
        request.setBook(book);
        request.setUser(user);
        LocalDate localDate = LocalDate.now();
        request.setTime(localDate);
        requestService.create(request);
        model.addAttribute("requests", requestService.findAllByUserId(id));
        return "user_request";
    }

    @GetMapping("/{id}/rent")
    public String rent(@PathVariable int id, Model model) {
        List <Rent> rents = rentService.hasRead(id);
        model.addAttribute("rents", rents);
        return "user_rent";
    }

    @GetMapping("/{idUser}/rent/{id}")
    public String returnBook(@PathVariable int idUser, @PathVariable int id){
        LocalDate date = LocalDate.now();
        Rent rent = rentService.readById(id);
        int id1 = rent.getBook().getId();
        rent.setTimeReturned(date);
        rentService.backBook(id1);
        rentService.update(rent);
        return "redirect:/user/{idUser}/rent";
    }

    @GetMapping("/{id}/statistic")
    public String statistic(@PathVariable int id, Model model) {
        List <Rent> rents = rentService.hasRead(id);
        long days = 0;
        long days2 = 0;
        int countBook = 0;
        int countBook2 = 0;
        for (Rent r: rents) {
            if (r.getTimeReturned() != null) {
                long period = ChronoUnit.DAYS.between(r.getTimeTaken(), r.getTimeReturned());
                days += period;
            }
            else {
                long period2 = ChronoUnit.DAYS.between(r.getTimeTaken(), LocalDate.now());
                days2 += period2;
            }
            if (r.getTimeReturned() != null)
                countBook++;
        }
        model.addAttribute("days", days);
        model.addAttribute("countBook", countBook);
        model.addAttribute("days2", days2);
        model.addAttribute("countBook2", rents.size() - countBook);
        return "user_statistic";
    }



    @GetMapping("/{id}/update")
    public String update(@PathVariable int id, Model model) {
        User user = userService.readById(id);
        model.addAttribute("user", user);
//        model.addAttribute("roles", .getAll());
        System.out.println(user + "get method");
        return "update_user";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable(name = "id") int id, @RequestParam String name,
                         @RequestParam String surname,
                         @RequestParam() String password,
                         @RequestParam String email) {
        User user = userService.readById(id);
        System.out.println(user);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setUsername(email);
        if (password != null) {
            user.setPassword(password);
        }
        userService.update(user);
        return "redirect:/user/" + id + "/read";
    }

//    @GetMapping("/{id}/delete")
//    public String delete(@PathVariable("id") int id) {
//        userService.delete(id);
//        return "redirect:/users/all";
//    }

}
