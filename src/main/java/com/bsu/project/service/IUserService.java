package com.bsu.project.service;

import com.bsu.project.entity.User;

import java.math.BigDecimal;

/**
 * @author Gulshirin Berdiyeva
 */
public interface IUserService {
    User register(String username, String password);

    User login(String username, String password);

    void updateUserBalance(BigDecimal balance, long userId);

    long countAllClients();
}
