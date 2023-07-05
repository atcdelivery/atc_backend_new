package com.atcdilivery.spring.jwt.mongodb.repository;

import com.atcdilivery.spring.jwt.mongodb.entity.Products;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Products,String> {
    public Optional<Products> findById(String id);

    public List<Products> findByUserId(String userId);
}
