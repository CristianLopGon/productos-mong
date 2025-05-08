package es.cristian.productos.infrastructure.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import es.cristian.productos.domain.models.Product;
import es.cristian.productos.domain.ports.out.ProductRepository;
import es.cristian.productos.infrastructure.mapper.ProductMapper;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private final ProductMongoRepository mongoRepository;

	public ProductRepositoryImpl(ProductMongoRepository mongoRepository) {
		this.mongoRepository = mongoRepository;
	}

	@Override
	public List<Product> findAll() {
		return mongoRepository.findAll().stream().map(ProductMapper::toDomain).collect(Collectors.toList());
	}

}
