package com.atcdilivery.spring.jwt.mongodb.payload.directResponse.trackorder;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class TrackOrderResponse {
    @JsonProperty("ShipmentData")
    public ArrayList<ShipmentDatum> shipmentData;
}
