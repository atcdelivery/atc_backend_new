package com.atcdilivery.spring.jwt.mongodb.service;

import com.atcdilivery.spring.jwt.mongodb.payload.request.EditWareHouseRequest;
import com.atcdilivery.spring.jwt.mongodb.payload.request.WareHouseRequest;
import com.atcdilivery.spring.jwt.mongodb.payload.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface WarehouseService {
    Response createWareHouse(WareHouseRequest wareHouseRequest) throws JsonProcessingException;

    Response editWareHouse(EditWareHouseRequest editWareHouseRequest) throws JsonProcessingException;
}
