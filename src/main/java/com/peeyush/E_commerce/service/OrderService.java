package com.peeyush.E_commerce.service;

import com.peeyush.E_commerce.dto.response.OrderResponse;
import com.peeyush.E_commerce.entity.*;
import com.peeyush.E_commerce.enums.Status;
import com.peeyush.E_commerce.mapper.OrderMapper;
import com.peeyush.E_commerce.repository.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final PricingService pricingService;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository, OrderMapper orderMapper, PricingService pricingService){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderMapper = orderMapper;
        this.pricingService = pricingService;
    }

    public OrderResponse createOrder(Cart cart, User user){

        Order order = new Order();
        order.setStatus(Status.CONFIRMED);
        order.setCreatedAt(LocalDateTime.now());
        order.setUser(user);

        Order savedOrder = orderRepository.save(order);
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getCartItems()){

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            BigDecimal finalPrice = pricingService.taxInclusive(BigDecimal.valueOf(cartItem.getProduct().getProductPrice()));
            orderItem.setPriceAtPurchase(finalPrice);
            orderItem.setOrder(savedOrder);
            savedOrder.getOrderItems().add(orderItem);

            BigDecimal lineTotal = pricingService.calculateLineTotal(finalPrice, orderItem.getQuantity());
            totalAmount = totalAmount.add(lineTotal);

            orderItemRepository.save(orderItem);

            Product product = cartItem.getProduct();
            int revisedQuantity = product.getStockQuantity() - cartItem.getQuantity();
            product.setStockQuantity(revisedQuantity);

            productRepository.save(product);
        }
        savedOrder.setTotalAmount(totalAmount);

        return orderMapper.toOrderResponse(savedOrder);
    }

    public OrderResponse getOrder(Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return orderMapper.toOrderResponse(order);
    }

    @Transactional
    public void cancelOrder(Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(Status.CANCELED);
    }


}
