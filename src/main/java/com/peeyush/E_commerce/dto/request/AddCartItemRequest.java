package com.peeyush.E_commerce.dto.request;

import jakarta.validation.constraints.Min;

public record AddCartItemRequest(
        @Min(0) int quantity,
        Long productId
) {
}
