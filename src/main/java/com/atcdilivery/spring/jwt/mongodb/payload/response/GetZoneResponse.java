package com.atcdilivery.spring.jwt.mongodb.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetZoneResponse {
    private String originCity;
    private String destinationCity;
    private double distance;
    private String zone;
}
