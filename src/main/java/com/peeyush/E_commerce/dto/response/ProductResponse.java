package com.peeyush.E_commerce.dto.response;

public record ProductResponse(
        Long id,
        String productName,
        String description,
        int productPrice,
        int stockQuantity
) {
}
