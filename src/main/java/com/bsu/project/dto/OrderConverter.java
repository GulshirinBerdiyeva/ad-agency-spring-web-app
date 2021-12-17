package com.bsu.project.dto;

import com.bsu.project.entity.Ad;
import com.bsu.project.entity.Order;
import com.bsu.project.entity.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Gulshirin Berdiyeva
 */
@Component
public class OrderConverter {
    public Order convertToOrder(User user, Ad ad, Date orderTime, BigDecimal price) {
        Order order = new Order();

        order.setUser(user);
        order.setAd(ad);
        order.setOrderTime(orderTime);
        order.setPrice(price);
        order.setPayment(false);

        return order;
    }
}
