package com.atcdilivery.spring.jwt.mongodb.repository;

import com.atcdilivery.spring.jwt.mongodb.entity.OrderDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends MongoRepository<OrderDetails,String> {

    public List<OrderDetails> findAllByUserIdAndOrderLiveFalse(String userId);

    public List<OrderDetails> findAllByUserIdAndOrderLiveTrue(String userId);

    public OrderDetails findByWaybill(String uploadWbn);
}
