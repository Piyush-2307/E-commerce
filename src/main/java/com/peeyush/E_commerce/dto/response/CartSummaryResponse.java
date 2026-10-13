package com.peeyush.E_commerce.dto.response;

public record CartSummaryResponse(
        Long productId,
        String productName,
        Integer productPrice,
        Integer quantity,
        Integer subtotal
) {
}
