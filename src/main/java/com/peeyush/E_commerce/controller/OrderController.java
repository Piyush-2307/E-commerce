package com.peeyush.E_commerce.controller;

import com.peeyush.E_commerce.dto.response.OrderResponse;
import com.peeyush.E_commerce.service.OrderOrchestrator;
import com.peeyush.E_commerce.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderOrchestrator orderOrchestrator;

    public OrderController(OrderService orderService, OrderOrchestrator orderOrchestrator){
        this.orderService = orderService;
        this.orderOrchestrator = orderOrchestrator;
    }

    @PostMapping("/{userId}")
    public OrderResponse placeOrder(@PathVariable Long userId){
        return orderOrchestrator.placeOrder(userId);
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrder(@PathVariable Long orderId){
        return orderService.getOrder(orderId);
    }

    @PatchMapping("/{orderId}")
    public void cancelOrder(@PathVariable Long orderId){
        orderService.cancelOrder(orderId);
    }
}
