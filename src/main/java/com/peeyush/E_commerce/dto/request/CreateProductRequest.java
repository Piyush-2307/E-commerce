package com.peeyush.E_commerce.dto.request;

public record CreateProductRequest(
        String productName,
        String description,
        int productPrice,
        int stockQuantity
) {
}
