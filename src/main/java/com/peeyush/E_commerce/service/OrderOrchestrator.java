package com.peeyush.E_commerce.service;

import com.peeyush.E_commerce.dto.response.OrderResponse;
import com.peeyush.E_commerce.entity.Cart;
import com.peeyush.E_commerce.entity.CartItem;
import com.peeyush.E_commerce.entity.User;
import com.peeyush.E_commerce.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderOrchestrator {
    private final OrderService orderService;
    private final CartService cartService;
    private final UserRepository userRepository;

    public OrderOrchestrator(OrderService orderService, CartService cartService, UserRepository userRepository){
        this.orderService = orderService;
        this.cartService = cartService;
        this.userRepository = userRepository;
    }

    @Transactional
    public OrderResponse placeOrder(Long userId) {
        Cart cart = cartService.findCartEntity(userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if(cart.getCartItems().isEmpty()){
            throw new RuntimeException("Cannot place order with an empty cart");
        }

        for (CartItem cartItem : cart.getCartItems()){
            if (cartItem.getQuantity() > cartItem.getProduct().getStockQuantity()){
                throw new RuntimeException("Stock not available");
            }
            if (cartItem.getQuantity() <= 0) {
                throw new RuntimeException("Invalid item quantity");
            }
        }

        OrderResponse response = orderService.createOrder(cart, user);
        cartService.clearCart(cart.getId());
        return response;
    }
}
