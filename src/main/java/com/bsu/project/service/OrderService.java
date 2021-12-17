package com.bsu.project.service;

import com.bsu.project.calculation.AdPriceCalculator;
import com.bsu.project.data.DataException;
import com.bsu.project.data.ImageFileWriter;
import com.bsu.project.dto.AdConverter;
import com.bsu.project.dto.OrderConverter;
import com.bsu.project.entity.Ad;
import com.bsu.project.entity.Order;
import com.bsu.project.entity.User;
import com.bsu.project.repository.OrderRepository;
import com.bsu.project.validator.FileNameValidator;
import com.bsu.project.validator.TitleValidator;
import com.bsu.project.validator.TranslationTimeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * @author Gulshirin Berdiyeva
 */
@Service
public class OrderService extends AbstractService<Order> implements IOrderService {
    private static final long MAX_IMAGE_SIZE = 32_000_000_000L;

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final AdService adService;
    private final FileNameValidator fileNameValidator;
    private final TitleValidator titleValidator;
    private final TranslationTimeValidator translationTimeValidator;
    private final ImageFileWriter imageWriter;
    private final AdConverter adConverter;
    private final OrderConverter orderConverter;
    private final AdPriceCalculator adPriceCalculator;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserService userService,
                        AdService adService, FileNameValidator fileNameValidator,
                        TitleValidator titleValidator, TranslationTimeValidator translationTimeValidator,
                        ImageFileWriter imageWriter, AdConverter adConverter,
                        OrderConverter orderConverter, AdPriceCalculator adPriceCalculator) {
        super("Order", orderRepository);

        this.orderRepository = orderRepository;
        this.userService = userService;
        this.adService = adService;
        this.fileNameValidator = fileNameValidator;
        this.titleValidator = titleValidator;
        this.translationTimeValidator = translationTimeValidator;
        this.imageWriter = imageWriter;
        this.adConverter = adConverter;
        this.orderConverter = orderConverter;
        this.adPriceCalculator = adPriceCalculator;
    }


    @Override
    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    @Override
    public long countAdsByUserIdAndPayment(long userId) {
        return orderRepository.countAdsByUserIdAndPayment(userId);
    }

    @Transactional
    @Override
    public Order processOrder(MultipartFile multipartFile, String title, short translationTime, long userId) {
        User user = userService.findById(userId);

        String filename = multipartFile.getOriginalFilename();
        long fileSize = multipartFile.getSize();

        boolean isFileNameValid = fileNameValidator.isValid(filename);
        boolean isTitleValid = titleValidator.isValid(title);
        boolean isTranslationTimeValid = translationTimeValidator.isValid(Integer.toString(translationTime));
        if (!isFileNameValid || !isTitleValid || !isTranslationTimeValid || fileSize >= MAX_IMAGE_SIZE) {
            throw new ServiceException("Invalid input parameters!");
        }

        try {
            imageWriter.write(multipartFile, filename);

            Ad ad = adConverter.convertToAd(title, filename, fileSize, translationTime);
            Ad savedAd = adService.save(ad);

            BigDecimal price = adPriceCalculator.calculate(translationTime);

            Order order = orderConverter.convertToOrder(user, savedAd, new Date(), price);

            return save(order);

        } catch (DataException e) {
            throw  new ServiceException(e.getMessage(), e);
        }

    }

    @Transactional
    @Override
    public Order payOrder(long userId, long orderId) {
        User user = userService.findById(userId);

        Order order = findById(orderId);

        Ad ad = order.getAd();

        BigDecimal userBalance = user.getBalance();
        BigDecimal orderPrice = order.getPrice();

        if (userBalance.compareTo(orderPrice) != -1) {
            BigDecimal userNewBalance = userBalance
                    .subtract(orderPrice)
                    .setScale(2, RoundingMode.HALF_UP);

            user.setBalance(userNewBalance);

            ad.setPayment(true);

            order.setOrderTime(new Date());
            order.setPayment(true);

            User save = userService.save(user);
            Ad updatedAd = adService.save(ad);
            Order updatedOrder = save(order);

            return updatedOrder;

        } else {
            throw new ServiceException("Please, fill balance!");
        }
    }

    public Order getCurrentOrder(HttpServletRequest request) {
        Order order = (Order) request.getSession().getAttribute("order");
        Order updatedOrder = findById(order.getId());
        request.getSession().setAttribute("order", updatedOrder);

        return order;
    }

}
