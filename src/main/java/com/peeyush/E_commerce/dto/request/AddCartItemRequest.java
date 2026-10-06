package com.peeyush.E_commerce.dto.request;

public record AddCartItemRequest(
        int quantity,
        Long productId
) {
}
