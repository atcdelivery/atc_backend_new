package com.atcdilivery.spring.jwt.mongodb.controllers;

import com.atcdilivery.spring.jwt.mongodb.payload.directResponse.trackorder.TrackOrderResponse;
import com.atcdilivery.spring.jwt.mongodb.payload.request.EditWareHouseRequest;
import com.atcdilivery.spring.jwt.mongodb.payload.request.OrderDetailsReq;
import com.atcdilivery.spring.jwt.mongodb.payload.request.WareHouseRequest;
import com.atcdilivery.spring.jwt.mongodb.payload.response.Response;
import com.atcdilivery.spring.jwt.mongodb.service.WarehouseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth/warehouse/")
public class WareHouseController {

    @Autowired
    WarehouseService warehouseService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Response createWareHouse(@Valid @RequestBody WareHouseRequest wareHouseRequest) throws JsonProcessingException {
        return warehouseService.createWareHouse(wareHouseRequest);
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Response editWareHouse(@Valid @RequestBody EditWareHouseRequest editWareHouseRequest) throws JsonProcessingException {
        return warehouseService.editWareHouse(editWareHouseRequest);
    }



}
