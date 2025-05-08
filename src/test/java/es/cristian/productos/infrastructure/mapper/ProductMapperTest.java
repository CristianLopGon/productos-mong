package es.cristian.productos.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.cristian.productos.application.dto.ProductResponseDTO;
import es.cristian.productos.domain.models.Product;
import es.cristian.productos.infrastructure.db.ProductDocument;

@SpringBootTest
public class ProductMapperTest {

	Product p1 = new Product("1", "Camiseta Test", 100, Map.of("S", 5, "M", 0, "L", 10));
	ProductDocument doc = new ProductDocument("1", "Camiseta Test", 100, Map.of("S", 5, "M", 0, "L", 10));
	ProductResponseDTO response = new ProductResponseDTO("1", "Camiseta Test", 100, Map.of("S", 5, "M", 0, "L", 10),
			15.0);

	@Test
	void toDomainTest() {

		Product product = ProductMapper.toDomain(doc);
		assertAll(() -> assertNotNull(product), () -> assertEquals("1", product.getId()),
				() -> assertEquals("Camiseta Test", product.getName()),
				() -> assertEquals(100, product.getSalesUnits()));

	}

	@Test
	void toDtoTest() {
		ProductResponseDTO document = ProductMapper.toDto(p1, 15.0);
		assertAll(() -> assertNotNull(document), () -> assertEquals("1", document.id()),
				() -> assertEquals("Camiseta Test", document.name()), () -> assertEquals(100, document.salesUnits()));
	}
}
