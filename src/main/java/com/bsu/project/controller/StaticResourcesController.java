package com.bsu.project.controller;

import com.bsu.project.entity.Ad;
import com.bsu.project.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Gulshirin Berdiyeva
 */
@Controller
public class StaticResourcesController {
    private final AdService adService;

    @Autowired
    public StaticResourcesController(AdService adService) {
        this.adService = adService;
    }

    @GetMapping("/styles.css")
    public String getStyles() {
        return "../static/styles.css";
    }

    @GetMapping("/images/{id}")
    public void getImageByAdId(@PathVariable("id")Long id, HttpServletResponse response) {
        Ad ad = adService.findById(id);

        adService.readAdFile(ad, response);
    }
}
