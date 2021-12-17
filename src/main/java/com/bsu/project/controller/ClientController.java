package com.bsu.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Gulshirin Berdiyeva
 */
@Controller
public class ClientsController {

    @GetMapping("/clientProfile")
    public String getClientProfile() {
        return "clientProfile";
    }

    @GetMapping("/orderPage")
    public String getOrderPage() {
        return "order";
    }

}
