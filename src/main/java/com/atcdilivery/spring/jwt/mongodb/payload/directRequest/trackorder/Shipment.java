package com.atcdilivery.spring.jwt.mongodb.payload.directRequest.trackorder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Shipment {
    @JsonProperty("Status")
    public Status status;
    @JsonProperty("PickUpDate")
    public Date pickUpDate;
    @JsonProperty("NSLCode")
    public String nSLCode;
    @JsonProperty("Sortcode")
    public String sortcode;
    @JsonProperty("ReferenceNo")
    public String referenceNo;
    @JsonProperty("AWB")
    public String aWB;
}
