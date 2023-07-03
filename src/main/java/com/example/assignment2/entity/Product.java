package com.example.assignment2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String slug;

    @Column
    private String name;

    @Column
    private String reference;

    @Column
    private BigDecimal price;

    @Column
    private BigDecimal vat;

    @Column
    private boolean stockable;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Stock> stocks;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;


}

