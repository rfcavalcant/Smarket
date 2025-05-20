package org.example.stock.service;


import org.example.stock.dto.ProductDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StockService {

    private final RestTemplate restTemplate;

    public StockService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductDTO checkStock(Long productId) {
        String url = "http://product-service:8080/products/" + productId;
        return restTemplate.getForObject(url, ProductDTO.class);
    }
}