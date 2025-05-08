package es.cristian.productos.application.services.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.cristian.productos.application.dto.ProductRequestDTO;
import es.cristian.productos.application.dto.ProductResponseDTO;
import es.cristian.productos.domain.models.Product;
import es.cristian.productos.domain.ports.out.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductServiceImpl productService;

	@Test
	void sortedProducts_returnSortedByScoreTest() {

		Product p1 = new Product("1", "Producto A", 100, Map.of("S", 0, "M", 5, "L", 5));
		Product p2 = new Product("2", "Producto B", 50, Map.of("S", 0, "M", 0, "L", 5));

		when(productRepository.findAll()).thenReturn(List.of(p1, p2));

		ProductRequestDTO request = new ProductRequestDTO(0.7, 0.3);

		List<ProductResponseDTO> resultado = productService.getSortedProducts(request);

		assertAll(() -> assertEquals(2, resultado.size()),
				() -> assertTrue(resultado.get(0).score() >= resultado.get(1).score()),
				() -> assertEquals("Producto A", resultado.get(0).name()));
	}

	@Test
	void sortedProducts_returnEmptyListTest() {
		when(productRepository.findAll()).thenReturn(List.of());

		ProductRequestDTO request = new ProductRequestDTO(0.6, 0.4);
		List<ProductResponseDTO> resultado = productService.getSortedProducts(request);

		assertAll(() -> assertNotNull(resultado), () -> assertTrue(resultado.isEmpty()));
	}

	@Test
	void sortedProducts_returnScoreOkTest() {
		Product producto = new Product("1", "Test", 50, Map.of("S", 1, "M", 0, "L", 1));

		when(productRepository.findAll()).thenReturn(List.of(producto));

		ProductRequestDTO request = new ProductRequestDTO(0.5, 0.5);

		List<ProductResponseDTO> resultado = productService.getSortedProducts(request);

		double esperado = (50.0 / 50.0) * 0.5 + (2.0 / 3.0) * 0.5;
		assertEquals(esperado, resultado.get(0).score(), 0.01);
	}
}
