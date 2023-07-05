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
@Document(collection = "PackagesResponse")
public class PackagesResponse {
    @Id
    private String id;
    private String status;
    private String client;
    private Object sort_code;
    private List<String> remarks;
    private String waybill;
    private double cod_amount;
    private String payment;
    private boolean serviceable;
    private String refnum;
}
