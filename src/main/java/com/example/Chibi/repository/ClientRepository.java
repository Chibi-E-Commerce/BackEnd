package com.example.Chibi.repository;

import com.example.Chibi.model.ClientModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<ClientModel, Integer> {
}
