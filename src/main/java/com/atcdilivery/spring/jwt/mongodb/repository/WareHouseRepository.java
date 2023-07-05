package com.atcdilivery.spring.jwt.mongodb.repository;

import com.atcdilivery.spring.jwt.mongodb.entity.Warehouse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseRepository extends MongoRepository<Warehouse,String> {
    public Warehouse findByName(String name);
}
