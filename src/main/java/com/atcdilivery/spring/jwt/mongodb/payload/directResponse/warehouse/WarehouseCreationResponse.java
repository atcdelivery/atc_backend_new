package com.atcdilivery.spring.jwt.mongodb.payload.directResponse.warehouse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseCreationResponse {
    public Data data;
    public boolean success;
    public String error;
}
