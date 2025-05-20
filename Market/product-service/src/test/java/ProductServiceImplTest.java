import org.example.dto.ProductDTO;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.example.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductServiceImpl service;

    private ProductDTO sampleDTO;
    private Product sampleEntity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        sampleDTO = new ProductDTO();
        sampleDTO.setName("Produto A");
        sampleDTO.setDescription("Descrição A");
        sampleDTO.setPrice(new BigDecimal("9.99"));
        sampleDTO.setQuantity(5);

        sampleEntity = new Product();
        sampleEntity.setId(1L);
        sampleEntity.setName("Produto A");
        sampleEntity.setDescription("Descrição A");
        sampleEntity.setPrice(new BigDecimal("9.99"));
        sampleEntity.setQuantity(5);
    }

    @Test
    void testCreate() {
        when(repository.save(any(Product.class))).thenReturn(sampleEntity);
        ProductDTO result = service.create(sampleDTO);
        assertNotNull(result);
        assertEquals("Produto A", result.getName());
        verify(repository).save(any(Product.class));
    }

    @Test
    void testFindById_found() {
        when(repository.findById(1L)).thenReturn(Optional.of(sampleEntity));
        ProductDTO result = service.findById(1L);
        assertEquals("Produto A", result.getName());
    }

    @Test
    void testFindById_notFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.findById(99L));
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(List.of(sampleEntity));
        List<ProductDTO> result = service.findAll();
        assertEquals(1, result.size());
    }

    @Test
    void testUpdate_success() {
        when(repository.findById(1L)).thenReturn(Optional.of(sampleEntity));
        when(repository.save(any(Product.class))).thenReturn(sampleEntity);

        ProductDTO updateDTO = new ProductDTO();
        updateDTO.setName("Atualizado");
        updateDTO.setDescription("Nova descrição");
        updateDTO.setPrice(new BigDecimal("15.00"));
        updateDTO.setQuantity(10);

        ProductDTO result = service.update(1L, updateDTO);

        assertEquals("Atualizado", result.getName());
        verify(repository).save(any(Product.class));
    }

    @Test
    void testDelete() {
        service.delete(1L);
        verify(repository).deleteById(1L);
    }
}
