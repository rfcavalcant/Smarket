package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter

public class ProductDTO {



    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;

    // Getters e setters
}