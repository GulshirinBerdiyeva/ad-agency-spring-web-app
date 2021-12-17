package com.bsu.project.service;

import com.bsu.project.entity.Order;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
@author Gulshirin Berdiyeva
*/
public interface IOrderService {
    List<Order> findAllByUserId(Long userId);

    long countAdsByUserIdAndPayment(long userId);

    Order processOrder(MultipartFile multipartFile, String title, short translationTime, long userId);

    Order payOrder(long userId, long orderId);
}
