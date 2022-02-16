package com.company.jcc.controller;

import com.company.jcc.config.Security;
import com.company.jcc.model.User;
import com.company.jcc.service.UserService;
import com.company.jcc.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Controller
public class MainController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/form-login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.getClass() == User.class) {
            int id = ((User) principal).getId();
            String name = ((User) principal).getName();
            model.addAttribute("name", name);
            model.addAttribute("id", id);
        }
        else {
            model.addAttribute("name", "admin");
        }
        return "hello_user2";
    }

    @GetMapping("/xxx")
    public String xxx(){
        return "redirect:/form-login";
    }

    @PostMapping("/success")
    public String success(){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "redirect:/";
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "create_user";
    }

    @PostMapping("/users/create")
    public String create(@Validated @ModelAttribute("user") User user) {
        LocalDate localDate = LocalDate.now();
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        user.setDateRegistered(localDate);
        user.setUsername(user.getEmail());
        user.setAge(user.getAge());
        userService.create(user);
        return "redirect:/form-login";
    }
//    @PostMapping("/saveMe")
//    public String saveMe(User user){
//        String encode = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encode);
//        userService.create(user);
//        return "redirect:/";
//    }

}
