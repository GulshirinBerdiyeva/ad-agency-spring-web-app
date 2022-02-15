package com.bsu.project.controller;

import com.bsu.project.entity.User;
import com.bsu.project.repository.RepositoryException;
import com.bsu.project.service.OrderService;
import com.bsu.project.service.ServiceException;
import com.bsu.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @author Gulshirin Berdiyeva
 */
@Controller
public class ClientController {
    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public ClientController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/clientProfile")
    public String getClientProfile(HttpServletRequest request, Model model) {
        try {
            User user = userService.getCurrentUser(request);
            model.addAttribute("user", user);

            long userAdsAmount = orderService.countAdsByUserIdAndPayment(user.getId());
            model.addAttribute("userAdsAmount", userAdsAmount);

            return "clientProfile";

        } catch (RepositoryException exception) {
            model.addAttribute("rError", exception.getMessage());
            return "redirect:/logout";
        }
    }

    @PostMapping("/fillBalance")
    public String fillBalance(@RequestParam("balanceValue")BigDecimal balanceValue,
                              HttpServletRequest request, RedirectAttributes model) {
        try {
            User user = (User) request.getSession().getAttribute("user");

            userService.updateUserBalance(balanceValue, user.getId());

        } catch (ServiceException exception) {
            model.addFlashAttribute("sError", exception.getMessage());

        } catch (RepositoryException exception) {
            model.addFlashAttribute("rError", exception.getMessage());
            return "redirect:/logout";
        }

        return "redirect:/clientProfile";
    }

}
