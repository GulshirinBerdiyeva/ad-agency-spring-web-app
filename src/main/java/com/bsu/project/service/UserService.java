package com.bsu.project.service;

import com.bsu.project.dto.ClientConverter;
import com.bsu.project.entity.User;
import com.bsu.project.repository.RepositoryException;
import com.bsu.project.repository.UserRepository;
import com.bsu.project.validator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gulshirin Berdiyeva
 */
@Service
public class UserService extends AbstractService<User> implements IUserService {
    private final UserRepository userRepository;
    private final FullNameValidator fullNameValidator;
    private final PasswordValidator passwordValidator;
    private final PasswordEncoder passwordEncoder;
    private final DoubleNumValidator doubleNumValidator;
    private final ClientConverter clientConverter;

    @Autowired
    public UserService(UserRepository userRepository,
                       FullNameValidator fullNameValidator, PasswordValidator passwordValidator,
                       PasswordEncoder passwordEncoder, DoubleNumValidator doubleNumValidator,
                       ClientConverter clientConverter) {
        super("User", userRepository);

        this.userRepository = userRepository;
        this.fullNameValidator = fullNameValidator;
        this.passwordValidator = passwordValidator;
        this.passwordEncoder = passwordEncoder;
        this.doubleNumValidator = doubleNumValidator;
        this.clientConverter = clientConverter;
    }

    @Transactional
    @Override
    public User register(String username, String password) {
        checkUsernameAndPassword(username, password);

        List<User> users = userRepository.findUserByUsername(username);

        boolean existUser = users.stream()
                .anyMatch(user -> passwordEncoder.matches(password, user.getPassword()));
        if (existUser) {
            throw new RepositoryException("User already exist!");
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = clientConverter.convertToClient(username, encodedPassword);

        return save(user);
    }

    @Override
    public User login(String username, String password) {
        checkUsernameAndPassword(username, password);

        List<User> users = userRepository.findUserByUsername(username);

        List<User> uniqueUser = users.stream()
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .collect(Collectors.toList());

        if (uniqueUser.size() != 1) {
            throw new RepositoryException("Not unique user!");
        } else {
            return uniqueUser.get(0);
        }
    }

    @Transactional
    @Override
    public void updateUserBalance(BigDecimal balance, long userId) {
        User user = findById(userId);

        boolean isValid = doubleNumValidator.isValid(balance.toString());
        if (!isValid) {
            throw new ServiceException("Invalid balance parameters!");
        }

        BigDecimal currentBalance = user.getBalance();
        BigDecimal newBalance = currentBalance.add(balance).setScale(2, RoundingMode.HALF_UP);

        userRepository.updateUserBalance(newBalance, userId);
    }

    @Override
    public long countAllClients() {
        return userRepository.countAllClients();
    }

    private void checkUsernameAndPassword(String username, String password) throws ServiceException {
        if (!fullNameValidator.isValid(username) || !passwordValidator.isValid(password)) {
            throw new ServiceException("Invalid user parameters!");
        }
    }

    public User getCurrentUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        User updatedUser = findById(user.getId());
        request.getSession().setAttribute("user", updatedUser);

        return updatedUser;
    }

}
