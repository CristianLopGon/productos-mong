package es.cristian.productos.domain.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class ProductTest {

	// Test 0 sizes with 0.0 stock
	void stockRatio_calculatesOkWithZeroTest() {
		Product producto = new Product("1", "Test", 0, Map.of("S", 0, "M", 0, "L", 0));
		assertEquals(0.0, producto.getStockRatio());
	}

	// Test diff sizes, diff stocks
	@Test
	void stockRatio_calculatesOkTest() {
		Product producto = new Product("1", "Test", 10, Map.of("S", 0, "M", 9, "L", 0));
		assertEquals(1.0 / 3.0, producto.getStockRatio(), 0.001);
	}
}
