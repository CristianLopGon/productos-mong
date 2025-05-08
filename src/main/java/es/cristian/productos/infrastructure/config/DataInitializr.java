package es.cristian.productos.infrastructure.config;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.cristian.productos.infrastructure.db.ProductDocument;
import es.cristian.productos.infrastructure.repository.ProductMongoRepository;

@Configuration
public class DataInitializr {

	private final Logger logger = LoggerFactory.getLogger(DataInitializr.class);

	@Bean
	CommandLineRunner initDatabase(ProductMongoRepository repository, ObjectMapper mapper) {
		return cmd -> {
			if (repository.count() == 0) {
				InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/products.json");

				List<ProductDocument> productos = mapper.readValue(inputStream, new TypeReference<>() {
				});

				repository.saveAll(productos);
				logger.info("Products loaded from JSON");
			} else {
				logger.info("Products were loaded before");
			}
		};
	}
}
