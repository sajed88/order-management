package com.example.assignment2.repositories;

import com.example.assignment2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByName(String name);
    Optional<Product> findByReference(String reference);
    List<Product> findByStockableTrue();
    Boolean existsByName(String Name);
    Boolean existsByReference(String reference);
}
