package es.cristian.productos.infrastructure.db;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class ProductDocumentTest {

	@Test
	void productDocumentConstructorTest() {
		ProductDocument doc = new ProductDocument("1", "Camiseta Test", 100, Map.of("S", 5, "M", 0, "L", 10));

		assertAll(() -> assertEquals("1", doc.getId()), () -> assertEquals("Camiseta Test", doc.getName()),
				() -> assertEquals(100, doc.getSalesUnits()), () -> assertEquals("Camiseta Test", doc.getName()));
	}
}
