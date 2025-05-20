package org.example.service;

import org.example.dto.ProductDTO;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    private ProductDTO mapToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        return dto;
    }

    private Product mapToEntity(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        return product;
    }

    @Override
    public ProductDTO create(ProductDTO dto) {
        Product product = repository.save(mapToEntity(dto));
        return mapToDTO(product);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO dto) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        return mapToDTO(repository.save(product));
    }

    @Override
    public ProductDTO findById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    public List<ProductDTO> findAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}