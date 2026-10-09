package com.peeyush.E_commerce.mapper;

import com.peeyush.E_commerce.dto.request.CreateProductRequest;
import com.peeyush.E_commerce.dto.response.ProductResponse;
import com.peeyush.E_commerce.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toProductEntity(CreateProductRequest request){
        return new Product(
                request.productName(),
                request.description(),
                request.productPrice(),
                request.stockQuantity()
        );
    }

    public ProductResponse toProductResponse(Product product){
        return new ProductResponse(
                product.getId(),
                product.getProductName(),
                product.getDescription(),
                product.getProductPrice(),
                product.getStockQuantity()
        );
    }
}
