package com.peeyush.E_commerce.mapper;

import com.peeyush.E_commerce.dto.response.CartItemResponse;
import com.peeyush.E_commerce.dto.response.CartResponse;
import com.peeyush.E_commerce.entity.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    public CartResponse toCartResponse(Cart cart){
        return new CartResponse(
                cart.getId(),
                cart.getCartItems()
                        .stream()
                        .map(cartItem -> new CartItemResponse(
                                cartItem.getId(),
                                cartItem.getProduct().getId(),
                                cartItem.getQuantity()
                        )).toList());
    }
}
