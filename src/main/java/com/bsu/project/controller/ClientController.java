package com.bsu.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {
    @GetMapping("/clientProfile")
    public String clientProfile() {
        return "clientProfile";
    }

    @GetMapping("/orderPage")
    public String orderPage() {
        return "order";
    }

}
