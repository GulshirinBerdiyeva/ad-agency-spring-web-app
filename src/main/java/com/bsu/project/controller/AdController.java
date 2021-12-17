package com.bsu.project.controller;

import com.bsu.project.entity.Ad;
import com.bsu.project.entity.User;
import com.bsu.project.repository.RepositoryException;
import com.bsu.project.service.AdService;
import com.bsu.project.service.ServiceException;
import com.bsu.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Gulshirin Berdiyeva
 */
@Controller
public class AdController {
    private final AdService adService;
    private final UserService userService;

    @Autowired
    public AdController(AdService adService, UserService userService) {
        this.adService = adService;
        this.userService = userService;
    }

    @GetMapping("/ads")
    public String getAds(HttpServletRequest request, Model model) {
        try {
            User user = userService.getCurrentUser(request);

            List<Ad> ads = adService.findAllAdByUser(user);

            model.addAttribute("ads", ads);

            return "ads";

        } catch (ServiceException exception) {
            model.addAttribute("sError", exception.getMessage());
            return "redirect:/clientProfile";

        } catch (RepositoryException exception) {
            model.addAttribute("rError", exception.getMessage());
            return "redirect:/logout";
        }
    }

}
