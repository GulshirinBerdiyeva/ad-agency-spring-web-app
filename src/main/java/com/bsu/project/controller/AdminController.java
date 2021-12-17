package com.bsu.project.controller;

import com.bsu.project.entity.User;
import com.bsu.project.service.AdService;
import com.bsu.project.service.DeviceService;
import com.bsu.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Gulshirin Berdiyeva
 */
@Controller
public class AdminController {
    private final UserService userService;
    private final AdService adService;
    private final DeviceService deviceService;

    @Autowired
    public AdminController(UserService userService, AdService adService,
                           DeviceService deviceService) {

        this.userService = userService;
        this.adService = adService;
        this.deviceService = deviceService;
    }

    @GetMapping("/adminProfile")
    public String getAdminProfile(HttpServletRequest request, Model model) {
        User user = userService.getCurrentUser(request);
        model.addAttribute("user", user);

        long usersAmount = userService.countAllClients();
        model.addAttribute("usersAmount", usersAmount);

        long adsAmount = adService.count();
        model.addAttribute("adsAmount", adsAmount);

        long devicesAmount = deviceService.count();
        model.addAttribute("devicesAmount", devicesAmount);

        return "adminProfile";
    }

}
