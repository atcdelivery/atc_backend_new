package com.atcdilivery.spring.jwt.mongodb.controllers;

import com.atcdilivery.spring.jwt.mongodb.entity.OrderDetails;
import com.atcdilivery.spring.jwt.mongodb.payload.directResponse.trackorder.TrackOrderResponse;
import com.atcdilivery.spring.jwt.mongodb.payload.request.OrderDetailsReq;
import com.atcdilivery.spring.jwt.mongodb.payload.response.GetZoneResponse;
import com.atcdilivery.spring.jwt.mongodb.payload.response.Response;
import com.atcdilivery.spring.jwt.mongodb.service.ApiService;
import com.atcdilivery.spring.jwt.mongodb.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth/order/")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ApiService apiService;

    @PostMapping("/create-order")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Response createOrder(@RequestBody OrderDetailsReq orderDetailsReq){
        return orderService.createOrder(orderDetailsReq);
    }

    @PostMapping("/edit-order/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Response editOrder(@RequestBody OrderDetailsReq orderDetailsReq,@PathVariable String id){
        return orderService.editOrder(orderDetailsReq,id);
    }

    @GetMapping("/create-order-delivery")
    @PreAuthorize("hasRole('ADMIN')")
    public Response createOrderDelivery(@RequestParam String orderDataBasesId) throws JsonProcessingException {
        return apiService.createOrderDilivery(orderDataBasesId);
    }

    @GetMapping("/get-order-manifest")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<OrderDetails> getMenifestOders(@RequestParam String userId){
        return orderService.getManifestOrders(userId);
    }

    @GetMapping("/get-my-orders")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<OrderDetails> getMyOrders(@RequestParam String userId){
        return orderService.getMyOrders(userId);
    }

    @GetMapping("/get-zone")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public GetZoneResponse rateCalculator(@RequestParam int originPincode, @RequestParam int destinationPincode){
        return orderService.getZoneTest(originPincode,destinationPincode);
    }





}
