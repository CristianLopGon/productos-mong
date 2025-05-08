package es.cristian.productos.domain.ports.out;

import java.util.List;

import es.cristian.productos.domain.models.Product;

public interface ProductRepository {
	List<Product> findAll();
}
