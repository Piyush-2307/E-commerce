package com.peeyush.E_commerce.dto.response;

import java.util.List;

public record CartResponse(
        Long id,
        List<CartItemResponse> items
) {
}
