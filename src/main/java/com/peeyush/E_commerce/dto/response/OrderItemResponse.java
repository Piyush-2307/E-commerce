package com.peeyush.E_commerce.dto.response;

public record OrderItemResponse(
        Long id,
        Long productId,
        int quantity,
        int priceAtPurchase
) {
}
