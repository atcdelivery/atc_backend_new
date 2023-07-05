package com.atcdilivery.spring.jwt.mongodb.serviceImpl;

import com.atcdilivery.spring.jwt.mongodb.entity.OrderDetails;
import com.atcdilivery.spring.jwt.mongodb.payload.directRequest.trackorder.Status;
import com.atcdilivery.spring.jwt.mongodb.payload.directRequest.trackorder.WebhookTrackingRequest;
import com.atcdilivery.spring.jwt.mongodb.repository.OrderDetailsRepository;
import com.atcdilivery.spring.jwt.mongodb.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebhookServiceImplementation implements WebhookService {

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Override
    public ResponseEntity<?> orderTrackingStatusUpdate(WebhookTrackingRequest webhookTrackingRequest){
        List<Status> status = new ArrayList<>();
        OrderDetails orderDetails = orderDetailsRepository.findByWaybill(webhookTrackingRequest.getShipment().getAWB());
        if (ObjectUtils.isEmpty(orderDetails)){
            return ResponseEntity.ok(HttpStatus.OK);
        }else {
            status.add(webhookTrackingRequest.getShipment().getStatus());
            try {
                orderDetails.getTrackingStatus().add(webhookTrackingRequest.getShipment().getStatus());
            }catch (Exception e){
                orderDetails.setTrackingStatus(status);
            }
            orderDetailsRepository.save(orderDetails);
            return ResponseEntity.ok(HttpStatus.OK);
        }

    }
}
