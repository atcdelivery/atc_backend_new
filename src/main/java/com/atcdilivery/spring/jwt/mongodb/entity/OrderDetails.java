package com.atcdilivery.spring.jwt.mongodb.entity;

import com.atcdilivery.spring.jwt.mongodb.payload.directRequest.trackorder.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("OrderDetails")
public class OrderDetails {

    @Transient
    public static final String SEQUENCE_NAME = "order_sequence";

    @Id
    private String id;

    private String warehouseName;
    private String pickupName;
    private String pickupEmail;
    private String pickupMobile;
    private String pickupAlternative_mobile;
    private long pickupPincode;
    private String pickupCity;
    private String pickupState;
    private String pickupAddress;
    private String pickupLandmark;
//
    private String sellerName;
    private String sellerAddress;

    private String returnName;
    private String returnEmail;
    private String returnMobile;
    private String returnAlternative_mobile;
    private long returnPincode;
    private String returnCity;
    private String returnState;
    private String returnAddress;
    private String returnLandmark;

    private String DropName;
    private String DropEmail;
    private String DropMobile;
    private String DropAlternative_mobile;
    private long DropPincode;
    private String DropCity;
    private String DropState;
    private String DropAddress;
    private String DropLandmark;

    private String productName;
    private String productCategory;
    private int productQuantity;
    private double productPrice;
//  New
    private Boolean fragile;
    private String invoiceDate;
    private boolean orderLive;

    private String paymentMode;
    private double totalAmount;
    private double totalTaxes;
    private double actualWeight;
    private double VolumentricWeight;

    private String orderId;
    private Date createDate;
    private Date modifyDate;
    private String userId;
    private boolean sameReturnOrder;

//  New Details
    private String zone;
//  DeliveryCharges
    private double amount;

//  new Details
    private String waybill;
    private String uploadWbn;
    private List<Status> trackingStatus;
    private String orderResponseId;
}
