package com.atcdilivery.spring.jwt.mongodb.payload.directResponse.trackorder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Scan{
    @JsonProperty("ScanDetail")
    public ScanDetail scanDetail;
}
