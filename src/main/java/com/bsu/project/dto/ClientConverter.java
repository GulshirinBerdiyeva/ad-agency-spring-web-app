package com.bsu.project.dto;

import com.bsu.project.entity.User;
import com.bsu.project.entity.UserRole;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Gulshirin Berdiyeva
 */
@Component
public class ClientConverter {
    public User convertToClient(String username, String password) {
        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setRole(UserRole.CLIENT);
        user.setBalance(new BigDecimal("0.00"));

        return user;
    }
}
