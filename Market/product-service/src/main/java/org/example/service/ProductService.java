package org.example.service;


import org.example.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    ProductDTO create(ProductDTO dto);
    ProductDTO update(Long id, ProductDTO dto);
    ProductDTO findById(Long id);
    List<ProductDTO> findAll();
    void delete(Long id);
}