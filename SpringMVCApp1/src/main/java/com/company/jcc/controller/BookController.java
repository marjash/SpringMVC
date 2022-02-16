package com.company.jcc.controller;

import com.company.jcc.model.Author;
import com.company.jcc.model.AuthorName;
import com.company.jcc.model.Book;
import com.company.jcc.model.Rent;
import com.company.jcc.service.BookService;
import com.company.jcc.service.impl.AuthorNameServiceImpl;
import com.company.jcc.service.impl.AuthorServiceImpl;
import com.company.jcc.service.impl.BookServiceImpl;
import com.company.jcc.service.impl.RentServiceImpl;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


import java.time.Period;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private AuthorServiceImpl authorService;

    @Autowired
    private AuthorNameServiceImpl authorNameService;

    @Autowired
    private RentServiceImpl rentService;

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("books", authorNameService.getAll());
        return "book_list2";
    }

//    @GetMapping("/search/byauthor")
//    public String search(Model model){
//    List<AuthorName> authorNames = authorNameService.getAll();
//    model.addAttribute("books", authorNames);
//    return "index4";
//    }
//
//    @PostMapping("/search/byauthor")
//    public String searchBookByTitle(@RequestParam String author, Model model){
//        model.addAttribute("book", authorNameService.readByAuthor(author));
//        System.out.println(author);
//        return "index5";
//    }

    @GetMapping("/search")
    public String home(Book book, Model model, String bookTitle) {
        List<AuthorName> books = authorNameService.getAll();
        if (bookTitle != null) {
            AuthorName authorName = authorNameService.readByTitle(bookTitle);
            model.addAttribute("authorName", authorName);
            return "index3";
        } else {
            model.addAttribute("books", books);
            return "index2";
        }
    }

    @GetMapping("/search/byauthor")
    public String search(Author author2, Model model, String author) {
        List<AuthorName> authorNames = authorNameService.getAll();
        if (author != null) {
            authorNames = authorNameService.readByAuthor(author);
            List<AuthorName> authorNames1 = authorNameService.readByCoAuthor(author);
            authorNames.addAll(authorNames1);
        }
        model.addAttribute("books", authorNames);
        return "index4";
    }

    @GetMapping("/statistic")
    public String statistic(){
        return "book_statistic";
    }

    @PostMapping("/statistic")
    public String statistic(@RequestParam("dateStart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
                            @RequestParam("dateEnd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd,
                            Model model){
        model.addAttribute("dateStart", dateStart);
        model.addAttribute("dateEnd", dateEnd);
        Rent mostPopular = rentService.getMostPopular(dateStart, dateEnd);
        Rent mostUnpopular = rentService.getMostUnpopular(dateStart, dateEnd);
        model.addAttribute("rent", mostPopular);
        model.addAttribute("rent2", mostUnpopular);
        return "rent_list2";
    }


    public BookController() {
    }

    public BookController(BookServiceImpl bookService, AuthorServiceImpl authorService, AuthorNameServiceImpl authorNameService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.authorNameService = authorNameService;
    }

    public AuthorNameServiceImpl getAuthorNameService() {
        return authorNameService;
    }

    public void setAuthorNameService(AuthorNameServiceImpl authorNameService) {
        this.authorNameService = authorNameService;
    }

    public BookService getBookService() {
        return bookService;
    }

    public void setBookService(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    public AuthorServiceImpl getAuthorService() {
        return authorService;
    }

    public void setAuthorService(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }
}