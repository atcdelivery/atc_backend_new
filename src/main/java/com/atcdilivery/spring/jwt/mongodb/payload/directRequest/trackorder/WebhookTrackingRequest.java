package com.atcdilivery.spring.jwt.mongodb.payload.directRequest.trackorder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WebhookTrackingRequest {
    @JsonProperty("Shipment")
    public Shipment shipment;
}
