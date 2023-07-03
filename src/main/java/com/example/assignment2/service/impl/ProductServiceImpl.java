package com.example.assignment2.service.impl;

import com.example.assignment2.entity.Product;
import com.example.assignment2.payload.ProductDto;
import com.example.assignment2.repositories.ProductRepository;
import com.example.assignment2.service.ProductService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;


    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }
    @Override
    public ProductDto createProduct(ProductDto productDto) {

        Product product = new Product();
        product.setSlug(productDto.getSlug());
        product.setName(productDto.getName());
        product.setReference(productDto.getReference());
        product.setPrice(productDto.getPrice());
        product.setVat(productDto.getVat());
        product.setStockable(productDto.getStockable());

        Product savedProduct = productRepository.save(product);

        return mapProductToDto(savedProduct);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::mapProductToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(int productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return mapProductToDto(product);
        }
        throw new NoSuchElementException("Product not found with ID: " + productId);
    }

    @Override
    public ProductDto updateProduct(int productId, ProductDto productDto) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setSlug(productDto.getSlug());
            product.setName(productDto.getName());
            product.setReference(productDto.getReference());
            product.setPrice(productDto.getPrice());
            product.setVat(productDto.getVat());
            product.setStockable(productDto.getStockable());

            Product updatedProduct = productRepository.save(product);
            return mapProductToDto(updatedProduct);
        }
        throw new NoSuchElementException("Product not found with ID: " + productId);
    }

    @Override
    public void deleteProduct(int productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        } else {
            throw new NoSuchElementException("Product not found with ID: " + productId);
        }
    }

    private ProductDto mapProductToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setSlug(product.getSlug());
        productDto.setName(product.getName());
        productDto.setReference(product.getReference());
        productDto.setPrice(product.getPrice());
        productDto.setVat(product.getVat());
        productDto.setStockable(product.isStockable());
        return productDto;
    }
}
