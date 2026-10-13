package com.peeyush.E_commerce.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PricingService {
    public BigDecimal taxInclusive(BigDecimal basePrice){
        return basePrice
                .multiply(new BigDecimal("1.18"))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateLineTotal( BigDecimal unitPrice, int quantity){
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
