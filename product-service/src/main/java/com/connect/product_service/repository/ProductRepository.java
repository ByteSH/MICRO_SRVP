package com.connect.product_service.repository;

import com.connect.product_service.entry.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,ObjectId> {
}
