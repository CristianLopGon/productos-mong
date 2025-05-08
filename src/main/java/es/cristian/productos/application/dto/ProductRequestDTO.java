package es.cristian.productos.application.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

public class ProductRequestDTO {

	@DecimalMin(value = "0.0", inclusive = false, message = "salesWeight must be greater than 0")
	@DecimalMax(value = "1.0", message = "salesWeight must be less than or equal to 1")
	private double salesWeight;

	@DecimalMin(value = "0.0", inclusive = false, message = "stockRatioWeight must be greater than 0")
	@DecimalMax(value = "1.0", message = "stockRatioWeight must be less than or equal to 1")
	private double stockRatioWeight;

	public ProductRequestDTO() {
	}

	public ProductRequestDTO(double salesWeight, double stockRatioWeight) {
		this.salesWeight = salesWeight;
		this.stockRatioWeight = stockRatioWeight;
	}

	public double getSalesWeight() {
		return salesWeight;
	}

	public double getStockRatioWeight() {
		return stockRatioWeight;
	}
}
