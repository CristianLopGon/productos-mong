package es.cristian.productos.application.dto;

import java.util.Map;

public record ProductResponseDTO(String id, String name, int salesUnits, Map<String, Integer> stock, double score) {

}
