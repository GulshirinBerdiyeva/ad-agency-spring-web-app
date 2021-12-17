package com.bsu.project.service;

import com.bsu.project.data.DataException;
import com.bsu.project.data.ImageFileReader;
import com.bsu.project.entity.Ad;
import com.bsu.project.entity.Order;
import com.bsu.project.entity.User;
import com.bsu.project.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gulshirin Berdiyeva
 */
@Service
public class AdService extends AbstractService<Ad> implements IAdService {
    private final AdRepository adRepository;
    private  final ImageFileReader imageFileReader;

    @Autowired
    public AdService(AdRepository adRepository, ImageFileReader imageFileReader) {
        super("Ad", adRepository);

        this.adRepository = adRepository;
        this.imageFileReader = imageFileReader;
    }

    @Override
    public List<Ad> findAllAdByUser(List<Order> orders, User user) {
        List<Ad> ads = orders
                .stream()
                .filter(order -> order.getUser().getId() == user.getId())
                .map(Order::getAd)
                .collect(Collectors.toList());

        return ads;
    }

    @Override
    public void readAdFile(Ad ad, HttpServletResponse response) {
        try {
            imageFileReader.read(ad, response);
        } catch (DataException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
