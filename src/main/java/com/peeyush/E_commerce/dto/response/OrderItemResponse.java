package com.peeyush.E_commerce.dto.response;

import java.math.BigDecimal;

public record OrderItemResponse(
        Long id,
        Long productId,
        int quantity,
        BigDecimal subtotal
) {
}
