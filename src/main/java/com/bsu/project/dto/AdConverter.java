package com.bsu.project.dto;

import com.bsu.project.entity.Ad;
import org.springframework.stereotype.Component;

/**
 * @author Gulshirin Berdiyeva
 */
@Component
public class AdConverter {
    public Ad convertToAd(String title, String fileName, long fileSize, short translationTime) {
        Ad ad = new Ad();

        ad.setTitle(title);
        ad.setFileName(fileName);
        ad.setFileSize(fileSize);
        ad.setTranslationTime(translationTime);
        ad.setPayment(false);

        return ad;
    }
}
