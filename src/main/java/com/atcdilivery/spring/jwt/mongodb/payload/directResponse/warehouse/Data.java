package com.atcdilivery.spring.jwt.mongodb.payload.directResponse.warehouse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    public BusinessHours business_hours;
    public String name;
    public ArrayList<String> business_days;
    public int pincode;
    public Object type_of_clientwarehouse;
    public String phone;
    public String client;
    public String address;
    public boolean active;
    public String message;
    public Object largest_vehicle_constraint;
}
