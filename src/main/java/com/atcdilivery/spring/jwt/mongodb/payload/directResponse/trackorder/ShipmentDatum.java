package com.atcdilivery.spring.jwt.mongodb.payload.directResponse.trackorder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShipmentDatum{
    @JsonProperty("Shipment")
    public Shipment shipment;
}
