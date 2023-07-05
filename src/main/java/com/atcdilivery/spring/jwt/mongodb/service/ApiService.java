package com.atcdilivery.spring.jwt.mongodb.service;

import com.atcdilivery.spring.jwt.mongodb.entity.Warehouse;
import com.atcdilivery.spring.jwt.mongodb.payload.directResponse.trackorder.TrackOrderResponse;
import com.atcdilivery.spring.jwt.mongodb.payload.request.EditWareHouseRequest;
import com.atcdilivery.spring.jwt.mongodb.payload.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface ApiService {

    Response createOrderDilivery(String orderId) throws JsonProcessingException;

    Response createWareHouse(Warehouse warehouse) throws JsonProcessingException;

    Response editWareHouse(EditWareHouseRequest editWareHouseRequest) throws JsonProcessingException;

    String getCityByPincode(int pincode);

    Double getDistance(int origin, int destination);

    ResponseEntity<?> trackMyOrder(String waybillNo);
    public TrackOrderResponse trackOrderResponse(String waybillNo);

}
