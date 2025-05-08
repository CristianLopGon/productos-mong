package es.cristian.productos.infrastructure.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cristian.productos.application.dto.ProductRequestDTO;
import es.cristian.productos.application.dto.ProductResponseDTO;
import es.cristian.productos.domain.ports.in.services.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	private final ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@PostMapping("/sort")
	public List<ProductResponseDTO> ordenarProductos(@RequestBody @Valid ProductRequestDTO requesst) {
		return service.getSortedProducts(requesst);
	}

}
