package com.atcdilivery.spring.jwt.mongodb.repository;

import com.atcdilivery.spring.jwt.mongodb.entity.CustomerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerDocumentRepository extends MongoRepository<CustomerDocument,String> {

}
