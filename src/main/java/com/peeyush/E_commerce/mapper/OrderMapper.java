package com.peeyush.E_commerce.mapper;

import com.peeyush.E_commerce.dto.response.OrderItemResponse;
import com.peeyush.E_commerce.dto.response.OrderResponse;
import com.peeyush.E_commerce.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponse toOrderResponse(Order order){
        return new OrderResponse(
                order.getId(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getCreatedAt(),
                order.getOrderItems()
                        .stream()
                        .map(orderItem -> new OrderItemResponse(
                                orderItem.getId(),
                                orderItem.getProduct().getId(),
                                orderItem.getQuantity(),
                                orderItem.getPriceAtPurchase())
                        ).toList());
    }
}
