package com.atcdilivery.spring.jwt.mongodb.repository;

import com.atcdilivery.spring.jwt.mongodb.entity.ProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends MongoRepository<ProductCategory,String> {

    public Optional<ProductCategory> findById(String id);

    public List<ProductCategory> findByCategoryName(String name);
}
