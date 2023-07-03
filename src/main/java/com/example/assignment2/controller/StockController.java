package com.example.assignment2.controller;

import com.example.assignment2.payload.StockDto;
import com.example.assignment2.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD REST APIs for Stock Resource")
@RestController
@RequestMapping("/api/v1")
public class StockController {

    private StockService stockService;
    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @ApiOperation(value = "Create Stock REST API")
    @PostMapping("/stocks")
    public ResponseEntity<StockDto> createStock(@Valid @RequestBody StockDto stockDto) {
        return new ResponseEntity<>(stockService.createStock(stockDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Stocks REST API")
    @GetMapping("/stocks")
    public List<StockDto> getAllStocks() {
        return stockService.getAllStocks();
    }

    @ApiOperation(value = "Get Stock By ID REST API")
    @GetMapping("/stocks/{id}")
    public ResponseEntity<StockDto> getStockById(@PathVariable(value = "id") int stockId) {
        StockDto stockDto = stockService.getStockById(stockId);
        return new ResponseEntity<>(stockDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Update Stock By ID REST API")
    @PutMapping("/stocks/{id}")
    public ResponseEntity<StockDto> updateStock(@PathVariable(value = "id") int stockId,
                                                @Valid @RequestBody StockDto stockDto) {
        StockDto updatedStock = stockService.updateStock(stockId, stockDto);
        return new ResponseEntity<>(updatedStock, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Stock By ID REST API")
    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable(value = "id") int stockId) {
        stockService.deleteStock(stockId);
        return new ResponseEntity<>("Stock deleted successfully", HttpStatus.OK);
    }
}

