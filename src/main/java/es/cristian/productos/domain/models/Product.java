package es.cristian.productos.domain.models;

import java.util.Map;

public class Product {

	private final String id;
	private final String name;
	private final int salesUnits;
	private final Map<String, Integer> stock;

	public Product(String id, String name, int salesUnits, Map<String, Integer> stock) {
		super();
		this.id = id;
		this.name = name;
		this.salesUnits = salesUnits;
		this.stock = stock;
	}

	public double getStockRatio() {
		if (stock == null || stock.isEmpty())
			return 0.0;
		long stockSizes = stock.values().stream().filter(units -> units > 0).count();
		return stockSizes / 3.0;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getSalesUnits() {
		return salesUnits;
	}

	public Map<String, Integer> getStock() {
		return stock;
	}

}
