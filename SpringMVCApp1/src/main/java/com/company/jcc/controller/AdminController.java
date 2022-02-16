package com.company.jcc.controller;

import com.company.jcc.config.EmailService;
import com.company.jcc.model.AuthorName;
import com.company.jcc.model.Book;
import com.company.jcc.model.Rent;
import com.company.jcc.model.User;
import com.company.jcc.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RequestServiceImpl requestService;

    @Autowired
    private RentServiceImpl rentService;

    @Autowired
    private AuthorServiceImpl authorService;

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private AuthorNameServiceImpl authorNameService;

    public AdminController(EmailService emailService, UserServiceImpl userService, RequestServiceImpl requestService) {
        this.emailService = emailService;
        this.userService = userService;
        this.requestService = requestService;
    }

    @GetMapping("users/all")
    public String getAll(Model model) {
        model.addAttribute("users", userService.getAll());
        return "user_list";
    }

    @GetMapping("/email/{id}")
    public String email(@PathVariable("id") int id){
        User user = userService.readById(id);
        String email = user.getEmail();
        String name = user.getName();
        emailService.sendSimpleMessage("hello "+ name, "test from my app", email);
        return "redirect:/admin/email/all";
    }

    @GetMapping("/email/all")
    public String email(){
        List<User> users = userService.getAll();
        for (User user: users) {
            String email = user.getEmail();
            emailService.sendSimpleMessage("hello", "test from my app", email);
        }
        return "redirect:/admin/email/all";
    }

    @GetMapping("/users/statistic")
    public String statistic(){
        return "users_statistic";
    }

    @PostMapping("/users/statistic")
    public String userStatistic(
            @RequestParam("dateStart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
            @RequestParam("dateEnd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd,
            Model model) {
        List<User> users = userService.getAll();
        long days = 0;
        for (User u: users) {
            long period = ChronoUnit.DAYS.between(u.getDateRegistered(), LocalDate.now());
            days += period;
        }
        int avg = (int) (days / users.size());
        model.addAttribute("dateStart", dateStart);
        model.addAttribute("dateEnd", dateEnd);
        model.addAttribute("avgAge", userService.avgAge());
        model.addAttribute("avgTime", avg);
        model.addAttribute("avgRequest", requestService.avgRequest(dateStart, dateEnd));
        System.out.println(requestService.avgRequest(dateStart, dateEnd));
        return "users_statistic2";
    }

    @GetMapping("/users/debtors")
    public String notReturned(Model model){
        model.addAttribute("users", rentService.findUsersNotReturnedInTime());
        return "debtors";
    }
    @GetMapping("/users/{id}/read")
    public String read(@PathVariable int id, Model model) {
        User user = userService.readById(id);
        List<Rent> rent = rentService.hasRead(id);
        LocalDate date = user.getDateRegistered();
        Period period = Period.between(date, LocalDate.now());
        model.addAttribute("user", user);
        model.addAttribute("rents", rent);
        model.addAttribute("period", period);
        return "user_info";
    }

    @GetMapping("/book/create")
    public String create(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAll());
        return "create_book2";
    }

    @PostMapping("/book/create")
    public String save(@Validated @ModelAttribute("book") Book book,
                       @RequestParam("authorId") int authorId,
                       @RequestParam("coauthorId") int coauthorId){
        Book book1 = bookService.create(book);
        AuthorName authorName = new AuthorName();
        authorName.setBook(book1);
        authorName.setAuthor(authorService.readById(authorId));
        if (coauthorId != 0)
            authorName.setCoauthor(authorService.readById(coauthorId));
        authorNameService.create(authorName);
        return "redirect:/books/all";
    }

//    @PostMapping("/create")
//    public String create(@Validated @ModelAttribute("book") Book book) {
//        bookService.create(book);
//        return "redirect:/books/all";
//    }

    @GetMapping("/book/{id}/read")
    public String readBook(@PathVariable int id, Model model) {
        model.addAttribute("book", authorNameService.readById(id));
        List<Rent> rents = bookService.averageTime(id);
        long days = 0;
        int avg;
        for (Rent r: rents) {
            long period = ChronoUnit.DAYS.between(r.getTimeTaken(), r.getTimeReturned());
            days += period;
        }
        if(rents.size() > 0) {
            avg = (int) (days / rents.size());
        }
        else {
            avg = (int) days;
        }
        System.out.println(avg);
        model.addAttribute("avg", avg);
        return "book_info2";
    }

    @GetMapping("/book/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books/all";
    }

    @GetMapping("/book/{id}/delete/one")
    public String deleteOne(@PathVariable("id") int id) {
        Book book = bookService.readById(id);
        book.setAmountLeft(book.getAmountLeft() - 1);
        bookService.update(book);
        return "redirect:/books/all";
    }

    @GetMapping("/book/{id}/update")
    public String update(@PathVariable("id") int id, Model model) {
        Book book = bookService.readById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAll());
        return "update_book";
    }


    @PostMapping("/book/{id}/update")
    public String update(@PathVariable("id") int id, @RequestParam String bookTitle,
                         @RequestParam int amountLeft,
                         @RequestParam int amountGave,
                         @RequestParam("authorId") int authorId,
                         @RequestParam("coAuthorId") int coAuthorId){
        Book book = bookService.readById(id);
        book.setBookTitle(bookTitle);
        book.setAmountLeft(amountLeft);
        book.setAmountGave(amountGave);
        AuthorName authorName = authorNameService.readById(id);
        if (coAuthorId != 0)
            authorName.setCoauthor(authorService.readById(coAuthorId));
        authorName.setAuthor(authorService.readById(authorId));
        bookService.update(book);
        authorNameService.update(authorName);
        return "redirect:/books/all";
    }

    @GetMapping("/requests/all")
    public String readAll(Model model) {
        model.addAttribute("requests", requestService.getAll());
        return "request_list";
    }
}
