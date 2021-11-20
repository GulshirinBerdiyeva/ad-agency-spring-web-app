package com.bsu.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class AuthorisationController {
    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/styles.css")
    public String styles() {
        return "../statics/styles.css";
    }

    @GetMapping("/registrationPage")
    public String registrationPage() {
        return "registration";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("ads", Arrays.asList(1, 2, 3));
        return "main";
    }

}
