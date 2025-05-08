package es.cristian.productos.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import es.cristian.productos.domain.models.Product;
import es.cristian.productos.domain.ports.out.ProductRepository;
import es.cristian.productos.infrastructure.db.ProductDocument;

@DataMongoTest
class ProductoRepositoryImplTest {

	@Autowired
	private ProductMongoRepository mongoRepo;

	@Test
	void findAll_returnsMappedProductos() {

		mongoRepo.deleteAll();
		mongoRepo.save(new ProductDocument("1", "Test", 100, Map.of("S", 1, "M", 2, "L", 3)));
		ProductRepository repo = new ProductRepositoryImpl(mongoRepo);

		List<Product> productos = repo.findAll();
		assertAll(() -> assertEquals(1, productos.size()), () -> assertEquals("Test", productos.get(0).getName()));
	}
}
