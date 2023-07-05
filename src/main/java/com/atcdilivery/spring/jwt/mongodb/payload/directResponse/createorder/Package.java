package com.atcdilivery.spring.jwt.mongodb.payload.directResponse.createorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Package {
    private String status;
    private String client;
    private Object sort_code;
    private ArrayList<String> remarks;
    private String waybill;
    private double cod_amount;
    private String payment;
    private boolean serviceable;
    private String refnum;
}
