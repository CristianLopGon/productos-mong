package es.cristian.productos.infrastructure.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cristian.productos.application.dto.ProductRequestDTO;
import es.cristian.productos.application.dto.ProductResponseDTO;
import es.cristian.productos.domain.ports.in.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	private final ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@Operation(summary = "You get a list sorted according to the search criteria")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = "application/json") }, description = "Sorted list of products"),
			@ApiResponse(responseCode = "400", description = "Of some of the criteria is incorrect or not present") })
	@PostMapping("/sort")
	public List<ProductResponseDTO> ordenarProductos(@RequestBody @Valid ProductRequestDTO requesst) {
		return service.getSortedProducts(requesst);
	}

}
