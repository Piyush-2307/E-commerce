package com.peeyush.E_commerce.controller;

import com.peeyush.E_commerce.dto.request.AddCartItemRequest;
import com.peeyush.E_commerce.dto.response.CartItemResponse;
import com.peeyush.E_commerce.dto.response.CartResponse;
import com.peeyush.E_commerce.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping("/users/{id}/item")
    public CartItemResponse addItem(@PathVariable Long id, @RequestBody AddCartItemRequest request){
        return cartService.addItem(id, request);
    }

    @GetMapping("/users/{id}/cart")
    public CartResponse findCart(@PathVariable Long id){
        return cartService.findCart(id);
    }

    @DeleteMapping("/{cartId}/item/{productId}")
    public void deleteCartItem(@PathVariable Long cartId,@PathVariable Long productId){
        cartService.deleteCartItem(cartId, productId);
    }
}
