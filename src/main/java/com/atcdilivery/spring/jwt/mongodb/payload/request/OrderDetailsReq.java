package com.atcdilivery.spring.jwt.mongodb.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsReq {

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
//    New
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

    private String dropName;
    private String dropEmail;
    private String dropMobile;
    private String dropAlternative_mobile;
    private long dropPincode;
    private String dropCity;
    private String dropState;
    private String dropAddress;
    private String dropLandmark;

    private boolean sameReturnOrder;
    private String productName;
    private String productCategory;
    private int productQuantity;
    private double productPrice;
    //  New
    private Boolean fragile;
    private String invoiceDate;

    private String paymentMode;
    private double totalAmount;
    private double totalTaxes;
    private double actualWeight;
    private double volumentricWeight;
    @NotBlank
    private String userId;
}
