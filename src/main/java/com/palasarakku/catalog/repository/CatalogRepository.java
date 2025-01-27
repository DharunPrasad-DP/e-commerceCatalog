package com.palasarakku.catalog.repository;

import com.palasarakku.catalog.domain.ProductDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CatalogRepository extends MongoRepository<ProductDetails,String> {
   Optional<ProductDetails> findByProductName(String searchTerm);
}
