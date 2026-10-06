package com.peeyush.E_commerce.repository;

import com.peeyush.E_commerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
