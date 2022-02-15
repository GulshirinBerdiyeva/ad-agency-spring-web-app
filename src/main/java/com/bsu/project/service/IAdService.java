package com.bsu.project.service;

import com.bsu.project.entity.Ad;
import com.bsu.project.entity.Order;
import com.bsu.project.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Gulshirin Berdiyeva
 */
public interface IAdService {
    List<Ad> findAllAdByUser(List<Order> orders, User user);

    void readAdFile(Ad ad, HttpServletResponse response);
}
