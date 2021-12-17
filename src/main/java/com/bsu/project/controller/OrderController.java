package com.bsu.project.controller;

import com.bsu.project.entity.Ad;
import com.bsu.project.entity.Order;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Gulshirin Berdiyeva
 */
@Controller
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/order")
    public String getOrderPage() {
        return "order";
    }

    @PostMapping("/makeOrder")
    public String makeOrder(@RequestParam("imageFile") MultipartFile multipartFile,
                            @RequestParam("title")String title,
                            @RequestParam("translationTime")short translationTime,
                            HttpServletRequest request, RedirectAttributes model) {
        try {
            User user = (User) request.getSession().getAttribute("user");

            Order order = orderService.processOrder(multipartFile, title, translationTime, user.getId());
            request.getSession().setAttribute("order", order);

            return "redirect:/checkout";

        } catch (ServiceException exception) {
            model.addFlashAttribute("sError", exception.getMessage());
            return "redirect:/order";

        } catch (RepositoryException exception) {
            model.addFlashAttribute("rError", exception.getMessage());
            return "redirect:/logout";
        }
    }

    @GetMapping("/checkout")
    public String getCheckoutPage(HttpServletRequest request, Model model) {
        try {
            User user = userService.getCurrentUser(request);

            Order order = orderService.getCurrentOrder(request);
            model.addAttribute("order", order);

            Ad ad = order.getAd();
            model.addAttribute("ad", ad);

            return "checkout";

        } catch (RepositoryException exception) {
            model.addAttribute("rError", exception.getMessage());
            return "redirect:/logout";
        }
    }

    @PostMapping("/pay")
    public String pay(HttpServletRequest request, RedirectAttributes model) {
        try {
            User user = userService.getCurrentUser(request);

            Order order = orderService.getCurrentOrder(request);

            Order updatedOrder = orderService.payOrder(user.getId(), order.getId());
            request.getSession().setAttribute("order", updatedOrder);

            userService.getCurrentUser(request);

            return "redirect:/ads";

        } catch (ServiceException exception) {
            model.addAttribute("sError", exception.getMessage());
            return "redirect:/clientProfile";

        } catch (RepositoryException exception) {
            model.addAttribute("rError", exception.getMessage());
            return "redirect:/logout";
        }
    }

}
