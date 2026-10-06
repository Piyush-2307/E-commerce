package com.peeyush.E_commerce.dto.response;

public record CartItemResponse(
        Long id,
        Long productId,
        int quantity
) {
}
