package com.example.assignment2.service;

import com.example.assignment2.payload.StockDto;

import java.util.List;

public interface StockService {
    StockDto createStock(StockDto stockDto);

    List<StockDto> getAllStocks();

    StockDto getStockById(int stockId);

    StockDto updateStock(int stockId, StockDto stockDto);

    void deleteStock(int stockId);

}
