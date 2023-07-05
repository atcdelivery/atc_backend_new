package com.atcdilivery.spring.jwt.mongodb.payload.directResponse.trackorder;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Status{
    @JsonProperty("Status")
    public String status;
    @JsonProperty("StatusLocation") 
    public String statusLocation;
    @JsonProperty("StatusDateTime") 
    public Date statusDateTime;
    @JsonProperty("RecievedBy") 
    public String recievedBy;
    @JsonProperty("Instructions") 
    public String instructions;
    @JsonProperty("StatusType") 
    public String statusType;
    @JsonProperty("StatusCode") 
    public String statusCode;
}
