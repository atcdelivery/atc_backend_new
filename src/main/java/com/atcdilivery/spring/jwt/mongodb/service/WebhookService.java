package com.atcdilivery.spring.jwt.mongodb.service;

import com.atcdilivery.spring.jwt.mongodb.payload.directRequest.trackorder.WebhookTrackingRequest;
import org.springframework.http.ResponseEntity;

public interface WebhookService {
    ResponseEntity<?> orderTrackingStatusUpdate(WebhookTrackingRequest webhookTrackingRequest);
}
