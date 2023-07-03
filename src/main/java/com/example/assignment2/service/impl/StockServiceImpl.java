package com.example.assignment2.service.impl;

import com.example.assignment2.entity.Product;
import com.example.assignment2.entity.Stock;
import com.example.assignment2.payload.StockDto;
import com.example.assignment2.repositories.ProductRepository;
import com.example.assignment2.repositories.StockRepository;
import com.example.assignment2.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private final StockRepository stockRepository;
    @Autowired
    private final ProductRepository productRepository;

    public StockServiceImpl(StockRepository stockRepository, ProductRepository productRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
    }

    @Override
    public StockDto createStock(StockDto stockDto) {
        Stock stock = new Stock();
        stock.setId(stockDto.getId());

        // Set other properties of the stock entity
        Product product = productRepository.findById(stockDto.getProductId())
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + stockDto.getProductId()));
        stock.setProduct(product);
        stock.setQuantity(stockDto.getQuantity());
        stock.setUpdatedAt(LocalDateTime.now());

        Stock savedStock = stockRepository.save(stock);

        return mapStockToDto(savedStock);
    }

    @Override
    public List<StockDto> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        return stocks.stream()
                .map(this::mapStockToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StockDto getStockById(int stockId) {
        Optional<Stock> stockOptional = stockRepository.findById(stockId);
        if (stockOptional.isPresent()) {
            Stock stock = stockOptional.get();
            return mapStockToDto(stock);
        }
        throw new NoSuchElementException("Stock not found with ID: " + stockId);
    }

    @Override
    public StockDto updateStock(int stockId, StockDto stockDto) {
        Optional<Stock> stockOptional = stockRepository.findById(stockId);
        if (stockOptional.isPresent()) {
            Stock stock = stockOptional.get();
            stock.setId(stockDto.getId());

            // Set other properties of the stock entity
            Product product = productRepository.findById(stockDto.getProductId())
                    .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + stockDto.getProductId()));
            stock.setProduct(product);
            stock.setQuantity(stockDto.getQuantity());
            stock.setUpdatedAt(LocalDateTime.now());

            Stock updatedStock = stockRepository.save(stock);
            return mapStockToDto(updatedStock);
        }
        throw new NoSuchElementException("Stock not found with ID: " + stockId);
    }

    @Override
    public void deleteStock(int stockId) {
        if (stockRepository.existsById(stockId)) {
            stockRepository.deleteById(stockId);
        } else {
            throw new NoSuchElementException("Stock not found with ID: " + stockId);
        }
    }

    private StockDto mapStockToDto(Stock stock) {
        StockDto stockDto = new StockDto();
        stockDto.setId(stock.getId());
        stockDto.setProductId(stock.getProduct().getId());
        stockDto.setQuantity(stock.getQuantity());
        stockDto.setUpdatedAt(stock.getUpdatedAt());
        return stockDto;
    }
}
