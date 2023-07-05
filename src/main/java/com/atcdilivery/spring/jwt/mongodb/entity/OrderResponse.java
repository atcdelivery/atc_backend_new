package com.atcdilivery.spring.jwt.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "OrderResponse")
public class OrderResponse {

    @Id
    private String id;
    private double cash_pickups_count;
    private int package_count;
    private String upload_wbn;
    private int replacement_count;
    private String rmk;
    private int pickups_count;
    private List<String> packagesIds;
    private double cash_pickups;
    private int cod_count;
    private boolean success;
    private int prepaid_count;
    private double cod_amount;
    private String orderId;
    private String orderDatabaseId;
}
