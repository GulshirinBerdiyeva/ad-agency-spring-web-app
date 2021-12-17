package com.bsu.project.controller;

import com.bsu.project.entity.User;
import com.bsu.project.entity.UserRole;
import com.bsu.project.repository.RepositoryException;
import com.bsu.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Gulshirin Berdiyeva
 */
@Controller
public class AuthenticationController {
    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/registrationPage")
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           HttpServletRequest request, RedirectAttributes model) {
        try {
            User user = userService.register(username, password);
            request.getSession().setAttribute("user", user);

            return "redirect:/clientProfile";

        } catch (ServiceException exception) {
            model.addFlashAttribute("sError", exception.getMessage());
            return "redirect:/registrationPage";

        } catch (RepositoryException exception) {
            model.addFlashAttribute("rError", exception.getMessage());
            return "redirect:/registrationPage";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletRequest request, RedirectAttributes model) {
        try {
            User user = userService.login(username, password);
            request.getSession().setAttribute("user", user);

            UserRole role = user.getRole();

            return role.equals(UserRole.ADMIN) ?  "redirect:/adminProfile" : "redirect:/clientProfile";

        } catch (ServiceException exception) {
            model.addFlashAttribute("sError", exception.getMessage());
            return "redirect:/";

        } catch (RepositoryException exception) {
            model.addFlashAttribute("rError", exception.getMessage());
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
