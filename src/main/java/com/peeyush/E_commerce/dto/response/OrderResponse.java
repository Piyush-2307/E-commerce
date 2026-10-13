package com.peeyush.E_commerce.dto.response;

import com.peeyush.E_commerce.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        Status status,
        BigDecimal totalAmount,
        LocalDateTime createdAt,
        List<OrderItemResponse> items
) {
}
