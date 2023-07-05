package com.atcdilivery.spring.jwt.mongodb.payload.directRequest.createorder;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PickupLocation {
    private String name;
    private String city;
    private int pin;
    private String country;
    private String phone;
    private String add;
}
