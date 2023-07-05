package com.atcdilivery.spring.jwt.mongodb.payload.directResponse.trackorder;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

public class Shipment{
    @JsonProperty("PickUpDate")
    public Date pickUpDate;
    @JsonProperty("Destination") 
    public String destination;
    @JsonProperty("DestRecieveDate") 
    public Object destRecieveDate;
    @JsonProperty("Scans") 
    public ArrayList<Scan> scans;
    @JsonProperty("Status") 
    public Status status;
    @JsonProperty("ReturnPromisedDeliveryDate") 
    public Object returnPromisedDeliveryDate;
    @JsonProperty("Ewaybill") 
    public ArrayList<Object> ewaybill;
    @JsonProperty("InvoiceAmount") 
    public int invoiceAmount;
    @JsonProperty("ChargedWeight") 
    public Object chargedWeight;
    @JsonProperty("PickedupDate") 
    public Object pickedupDate;
    @JsonProperty("DeliveryDate") 
    public Object deliveryDate;
    @JsonProperty("SenderName") 
    public String senderName;
    @JsonProperty("AWB") 
    public String aWB;
    @JsonProperty("DispatchCount") 
    public int dispatchCount;
    @JsonProperty("OrderType") 
    public String orderType;
    @JsonProperty("ReturnedDate") 
    public Object returnedDate;
    @JsonProperty("ExpectedDeliveryDate") 
    public Object expectedDeliveryDate;
    @JsonProperty("RTOStartedDate") 
    public Object rTOStartedDate;
    @JsonProperty("Extras") 
    public String extras;
    @JsonProperty("FirstAttemptDate") 
    public Object firstAttemptDate;
    @JsonProperty("ReverseInTransit") 
    public boolean reverseInTransit;
    @JsonProperty("Quantity") 
    public String quantity;
    @JsonProperty("Origin") 
    public String origin;
    @JsonProperty("Consignee") 
    public Consignee consignee;
    @JsonProperty("ReferenceNo") 
    public String referenceNo;
    @JsonProperty("OutDestinationDate") 
    public Object outDestinationDate;
    @JsonProperty("CODAmount") 
    public int cODAmount;
    @JsonProperty("PromisedDeliveryDate") 
    public Object promisedDeliveryDate;
    @JsonProperty("PickupLocation") 
    public String pickupLocation;
    @JsonProperty("OriginRecieveDate") 
    public Object originRecieveDate;
}
