package com.peeyush.E_commerce.repository;

import com.peeyush.E_commerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
