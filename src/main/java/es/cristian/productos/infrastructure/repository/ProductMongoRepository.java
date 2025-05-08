package es.cristian.productos.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import es.cristian.productos.infrastructure.db.ProductDocument;

public interface ProductMongoRepository extends MongoRepository<ProductDocument, String> {

}
