package com.atcdilivery.spring.jwt.mongodb.payload.directResponse.createorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderResponseObj {
    private double cash_pickups_count;
    private int package_count;
    private String upload_wbn;
    private int replacement_count;
    private String rmk;
    private int pickups_count;
    private ArrayList<Package> packages;
    private double cash_pickups;
    private int cod_count;
    private boolean success;
    private int prepaid_count;
    private double cod_amount;
}
