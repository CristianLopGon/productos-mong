package es.cristian.productos.infrastructure.mapper;

import es.cristian.productos.application.dto.ProductResponseDTO;
import es.cristian.productos.domain.models.Product;
import es.cristian.productos.infrastructure.db.ProductDocument;

public class ProductMapper {

	public static Product toDomain(ProductDocument doc) {
		return new Product(doc.getId(), doc.getName(), doc.getSalesUnits(), doc.getStock());
	}

	public static ProductResponseDTO toDto(Product product, double score) {
		return new ProductResponseDTO(product.getId(), product.getName(), product.getSalesUnits(), product.getStock(),
				score);
	}
}
