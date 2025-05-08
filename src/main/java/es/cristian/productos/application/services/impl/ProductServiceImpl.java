package es.cristian.productos.application.services.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import es.cristian.productos.application.dto.ProductRequestDTO;
import es.cristian.productos.application.dto.ProductResponseDTO;
import es.cristian.productos.domain.models.Product;
import es.cristian.productos.domain.ports.in.services.ProductService;
import es.cristian.productos.domain.ports.out.ProductRepository;
import es.cristian.productos.infrastructure.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository repository;

	public ProductServiceImpl(ProductRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ProductResponseDTO> getSortedProducts(ProductRequestDTO request) {
		List<Product> products = repository.findAll();
		if (products.isEmpty())
			return List.of(); // No debería pasar, pero se controla aquí

		int maxSales = products.stream().mapToInt(Product::getSalesUnits).max().orElse(1);

		return products.stream().map(product -> {
			double score = (product.getSalesUnits() / (double) maxSales) * request.getSalesWeight()
					+ product.getStockRatio() * request.getStockRatioWeight();

			return ProductMapper.toDto(product, score);
		}).sorted(Comparator.comparingDouble(p -> -p.score())).collect(Collectors.toList());
	}

}
