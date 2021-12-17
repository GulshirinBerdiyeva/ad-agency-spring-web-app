package com.bsu.project.calculation;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Gulshirin Berdiyeva
 */
@Component
public class AdPriceCalculator {
    private static final BigDecimal AD_PRICE_PER_HOUR = BigDecimal.valueOf(100L);

    public BigDecimal calculate(short translationTime) {
            return BigDecimal.valueOf(translationTime)
                    .multiply(AD_PRICE_PER_HOUR)
                    .setScale(2, RoundingMode.HALF_UP);

    }
}
