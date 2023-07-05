package com.atcdilivery.spring.jwt.mongodb.service;

import com.atcdilivery.spring.jwt.mongodb.entity.OrderDetails;
import com.atcdilivery.spring.jwt.mongodb.payload.request.OrderDetailsReq;
import com.atcdilivery.spring.jwt.mongodb.payload.response.GetZoneResponse;
import com.atcdilivery.spring.jwt.mongodb.payload.response.Response;

import java.util.List;

public interface OrderService {

    Response createOrder(OrderDetailsReq orderDetailsReq);

    String getZone(int originPincode, int destinationPincode);

    GetZoneResponse getZoneTest(int originPincode, int destinationPincode);

    Response editOrder(OrderDetailsReq orderDetailsReq, String id);

    List<OrderDetails> getManifestOrders(String userId);

    List<OrderDetails> getMyOrders(String userId);
}
