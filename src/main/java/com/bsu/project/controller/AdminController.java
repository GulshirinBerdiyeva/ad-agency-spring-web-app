package com.bsu.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/adminProfile")
    public String adminProfile() {
        return "adminProfile";
    }

}
