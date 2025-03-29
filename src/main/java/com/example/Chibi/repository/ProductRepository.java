package com.example.Chibi.repository;

import com.example.Chibi.model.ProductModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<ProductModel, ObjectId> {
    Optional<ProductModel> findByNome(String nome);
}
