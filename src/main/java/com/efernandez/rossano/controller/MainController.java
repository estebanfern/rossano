package com.efernandez.rossano.controller;

import com.efernandez.rossano.service.RolService;
import com.efernandez.rossano.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MainController {

    private UserService userService;

    @Autowired
    public MainController(UserService userService, RolService rolService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String main() {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}
