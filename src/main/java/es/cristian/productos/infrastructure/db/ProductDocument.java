package es.cristian.productos.infrastructure.db;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class ProductDocument {

	@Id
	private String id;
	private String name;
	private int salesUnits;
	private Map<String, Integer> stock;

	public ProductDocument() {
	}

	public ProductDocument(String id, String name, int salesUnits, Map<String, Integer> stock) {
		this.id = id;
		this.name = name;
		this.salesUnits = salesUnits;
		this.stock = stock;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalesUnits() {
		return salesUnits;
	}

	public void setSalesUnits(int salesUnits) {
		this.salesUnits = salesUnits;
	}

	public Map<String, Integer> getStock() {
		return stock;
	}

	public void setStock(Map<String, Integer> stock) {
		this.stock = stock;
	}
}
