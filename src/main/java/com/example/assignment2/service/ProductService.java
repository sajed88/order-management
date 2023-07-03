package com.example.assignment2.service;

import com.example.assignment2.payload.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(int productId);

    ProductDto updateProduct(int productId, ProductDto productDto);

    void deleteProduct(int productId);
}
