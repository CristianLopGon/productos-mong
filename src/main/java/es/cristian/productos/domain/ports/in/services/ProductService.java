package es.cristian.productos.domain.ports.in.services;

import java.util.List;

import es.cristian.productos.application.dto.ProductRequestDTO;
import es.cristian.productos.application.dto.ProductResponseDTO;

public interface ProductService {

	List<ProductResponseDTO> getSortedProducts(ProductRequestDTO request);
}
