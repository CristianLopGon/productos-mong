package es.cristian.productos.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import es.cristian.productos.domain.models.Product;
import es.cristian.productos.infrastructure.db.ProductDocument;

public class ProductRepositoryImplIntegrationTest {

	@Autowired
	private ProductMongoRepository repository;

	void findAll_debeDevolverProductoMapeado() {
		ProductDocument doc = new ProductDocument("1", "Camiseta Test", 100, Map.of("S", 5, "M", 0, "L", 10));
		repository.save(doc);

		ProductRepositoryImpl repo = new ProductRepositoryImpl(repository);

		List<Product> productos = repo.findAll();

		assertAll(() -> assertFalse(productos.isEmpty()),
				() -> assertEquals("Camiseta Test", productos.get(0).getName()),
				() -> assertEquals(100, productos.get(0).getSalesUnits()));
	}
}
