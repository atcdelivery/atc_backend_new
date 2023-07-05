package com.atcdilivery.spring.jwt.mongodb.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String categoryId;
    private String userId;
    private String categoryName;
    private String productName;
    private String productCode;
    private double productPrice;
    private int productQuantity;
}
