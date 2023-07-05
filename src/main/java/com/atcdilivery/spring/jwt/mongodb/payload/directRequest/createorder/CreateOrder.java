package com.atcdilivery.spring.jwt.mongodb.payload.directRequest.createorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrder {
    private ArrayList<Shipment> shipments;
    private PickupLocation pickup_location;
}
