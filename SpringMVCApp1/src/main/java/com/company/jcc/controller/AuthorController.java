package com.company.jcc.controller;


import com.company.jcc.model.Author;
import com.company.jcc.service.impl.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private final AuthorServiceImpl authorServiceImpl;

    public AuthorController(AuthorServiceImpl authorServiceImpl) {
        this.authorServiceImpl = authorServiceImpl;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("author", new Author());
        return "create_author";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("author") Author author) {
        authorServiceImpl.create(author);
        return "redirect:/author/" + author.getId() + "read";
    }
}
