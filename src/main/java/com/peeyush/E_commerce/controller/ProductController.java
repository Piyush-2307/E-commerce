package com.peeyush.E_commerce.controller;

import com.peeyush.E_commerce.dto.request.CreateProductRequest;
import com.peeyush.E_commerce.dto.request.UpdateProductRequest;
import com.peeyush.E_commerce.dto.response.ProductResponse;
import com.peeyush.E_commerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ProductResponse createProduct(@Valid @RequestBody CreateProductRequest request){
        return productService.createProduct(request);
    }

    @GetMapping("/{id}")
    public ProductResponse findProduct(@PathVariable Long id){
        return productService.findProduct(id);
    }

    @GetMapping
    public Page<ProductResponse> findAll(Pageable pageable){
        return productService.findAll(pageable);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Long id,@Valid @RequestBody UpdateProductRequest request){
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
