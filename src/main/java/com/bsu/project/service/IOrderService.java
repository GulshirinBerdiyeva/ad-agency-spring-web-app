package com.bsu.project.service;

import com.bsu.project.entity.Order;
import com.bsu.project.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
@author Gulshirin Berdiyeva
*/
public interface IOrderService {
    List<Order> findAllByUser(User user);

    long countAdsByUserIdAndPayment(long userId);

    Order processOrder(MultipartFile multipartFile, String title, short translationTime, long userId);

    Order payOrder(long userId, long orderId);
}
