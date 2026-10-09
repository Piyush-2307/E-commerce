package com.peeyush.E_commerce.service;

import com.peeyush.E_commerce.dto.request.CreateProductRequest;
import com.peeyush.E_commerce.dto.request.UpdateProductRequest;
import com.peeyush.E_commerce.dto.response.ProductResponse;
import com.peeyush.E_commerce.entity.Product;
import com.peeyush.E_commerce.mapper.ProductMapper;
import com.peeyush.E_commerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductResponse createProduct(CreateProductRequest request){
        Product product = productMapper.toProductEntity(request);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    public ProductResponse findProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toProductResponse(product);
    }

    public Page<ProductResponse> findAll(Pageable pageable){
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(productMapper::toProductResponse);
    }

    public ProductResponse updateProduct(Long id ,UpdateProductRequest request){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setProductName(request.productName());
        product.setDescription(request.description());
        product.setProductPrice(request.productPrice());
        product.setStockQuantity(request.stockQuantity());
        return productMapper.toProductResponse(productRepository.save(product));
    }

    public void deleteProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }
}
