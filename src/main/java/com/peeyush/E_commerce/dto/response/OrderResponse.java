package com.peeyush.E_commerce.dto.response;

import com.peeyush.E_commerce.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        Status status,
        int totalAmount,
        LocalDateTime createdAt,
        List<OrderItemResponse> items
) {
}
