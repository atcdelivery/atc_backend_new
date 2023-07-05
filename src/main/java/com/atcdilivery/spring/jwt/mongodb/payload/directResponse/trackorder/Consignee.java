package com.atcdilivery.spring.jwt.mongodb.payload.directResponse.trackorder;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Consignee{
    @JsonProperty("City")
    public String city;
    @JsonProperty("Name") 
    public String name;
    @JsonProperty("Country") 
    public String country;
    @JsonProperty("Address2") 
    public ArrayList<Object> address2;
    @JsonProperty("Address3") 
    public String address3;
    @JsonProperty("PinCode") 
    public int pinCode;
    @JsonProperty("State") 
    public String state;
    @JsonProperty("Telephone2") 
    public String telephone2;
    @JsonProperty("Telephone1") 
    public String telephone1;
    @JsonProperty("Address1") 
    public ArrayList<Object> address1;
}
