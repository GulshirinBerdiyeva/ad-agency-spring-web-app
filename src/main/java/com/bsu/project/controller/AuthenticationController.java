package com.bsu.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

/**
 * @author Gulshirin Berdiyeva
 */
@Controller
public class AuthorisationController {

    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/styles.css")
    public String getStyles() {
        return "../statics/styles.css";
    }

    @GetMapping("/registrationPage")
    public String getRegistrationPage() {
        return "registration";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    @GetMapping("/main")
    public String getMainPage(Model model) {
        model.addAttribute("ads", Arrays.asList(1, 2, 3));
        return "main";
    }

}
