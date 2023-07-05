package com.atcdilivery.spring.jwt.mongodb.payload.directRequest.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateWarehouseRequest {
    public String name;
    public String email;
    public String phone;
    public String address;
    public String city;
    public String country;
    public String pin;
    public String return_address;
    public String return_pin;
    public String return_city;
    public String return_state;
    public String return_country;
}
