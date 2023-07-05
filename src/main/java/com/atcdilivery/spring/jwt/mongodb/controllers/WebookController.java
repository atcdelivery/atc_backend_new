package com.atcdilivery.spring.jwt.mongodb.controllers;

import com.atcdilivery.spring.jwt.mongodb.entity.OrderDetails;
import com.atcdilivery.spring.jwt.mongodb.payload.directRequest.trackorder.WebhookTrackingRequest;
import com.atcdilivery.spring.jwt.mongodb.payload.request.ProductRequest;
import com.atcdilivery.spring.jwt.mongodb.payload.response.Response;
import com.atcdilivery.spring.jwt.mongodb.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/webhook/")
public class WebookController {

    @Autowired
    WebhookService webhookService;

    @PostMapping("/update-order-status")
    public ResponseEntity<?> updateOrderStatus(@RequestBody WebhookTrackingRequest webhookTrackingRequest,@RequestHeader String Authorization){
        System.out.println(webhookTrackingRequest);
        System.out.println(Authorization);
        if (Authorization.equals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")){
            return webhookService.orderTrackingStatusUpdate(webhookTrackingRequest);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
