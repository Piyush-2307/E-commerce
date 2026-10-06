package com.peeyush.E_commerce.dto.request;

public record UpdateProductRequest(
        String productName,
        String description,
        int productPrice,
        int stockQuantity
) {
}
