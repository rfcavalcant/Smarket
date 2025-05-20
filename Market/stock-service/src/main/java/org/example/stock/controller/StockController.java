package org.example.stock.controller;

import org.example.stock.dto.ProductDTO;
import org.example.stock.service.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{productId}")
    public Map<String, Object> getStockStatus(@PathVariable Long productId) {
        ProductDTO product = stockService.checkStock(productId);

        Map<String, Object> response = new HashMap<>();
        response.put("id", product.getId());
        response.put("name", product.getName());
        response.put("description", product.getDescription());
        response.put("price", product.getPrice());
        response.put("quantity", product.getQuantity());
        response.put("lowStock", product.getQuantity() < 10);

        return response;
    }
}