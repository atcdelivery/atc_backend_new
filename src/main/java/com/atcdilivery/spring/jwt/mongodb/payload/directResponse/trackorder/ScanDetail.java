package com.atcdilivery.spring.jwt.mongodb.payload.directResponse.trackorder;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ScanDetail{
    @JsonProperty("ScanDateTime")
    public Date scanDateTime;
    @JsonProperty("ScanType") 
    public String scanType;
    @JsonProperty("Scan") 
    public String scan;
    @JsonProperty("StatusDateTime") 
    public Date statusDateTime;
    @JsonProperty("ScannedLocation") 
    public String scannedLocation;
    @JsonProperty("Instructions") 
    public String instructions;
    @JsonProperty("StatusCode") 
    public String statusCode;
}
