package com.peeyush.E_commerce.service;

import com.peeyush.E_commerce.mapper.CartMapper;
import com.peeyush.E_commerce.repository.CartItemRepository;
import com.peeyush.E_commerce.repository.CartRepository;
import com.peeyush.E_commerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, CartMapper cartMapper){
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.cartMapper = cartMapper;
    }


}
