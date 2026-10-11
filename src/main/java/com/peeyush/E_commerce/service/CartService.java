package com.peeyush.E_commerce.service;

import com.peeyush.E_commerce.dto.request.AddCartItemRequest;
import com.peeyush.E_commerce.dto.response.CartItemResponse;
import com.peeyush.E_commerce.dto.response.CartResponse;
import com.peeyush.E_commerce.entity.Cart;
import com.peeyush.E_commerce.entity.CartItem;
import com.peeyush.E_commerce.entity.Product;
import com.peeyush.E_commerce.entity.User;
import com.peeyush.E_commerce.mapper.CartMapper;
import com.peeyush.E_commerce.repository.CartItemRepository;
import com.peeyush.E_commerce.repository.CartRepository;
import com.peeyush.E_commerce.repository.ProductRepository;
import com.peeyush.E_commerce.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, CartMapper cartMapper, UserRepository userRepository){
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.cartMapper = cartMapper;
        this.userRepository = userRepository;
    }

    public CartItemResponse addItem(Long id,  AddCartItemRequest request){
        Cart cart = cartRepository.findByUserId(id).orElseGet(() -> {
            User user = userRepository
                    .findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("User not found"));

            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });

        Product product = productRepository.findById(request.productId()).orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), request.productId()).orElse(null);

        if(cartItem != null){
            cartItem.setQuantity(cartItem.getQuantity() + request.quantity());
        }else {
            cartItem = new CartItem(request.quantity());
            cartItem.setProduct(product);
            cartItem.setCart(cart);
        }

        CartItem savedCartItem = cartItemRepository.save(cartItem);
        return  new CartItemResponse(savedCartItem.getId(), savedCartItem.getProduct().getId(), savedCartItem.getQuantity());
    }

    public CartResponse findCart(Long id){
        Cart cart = cartRepository.findByUserId(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        return cartMapper.toCartResponse(cart);
    }

    public void deleteCartItem(Long cartId, Long productId){
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cartId, productId).orElseThrow(() -> new RuntimeException("CartItem not found"));
        cartItemRepository.delete(cartItem);
    }
}
