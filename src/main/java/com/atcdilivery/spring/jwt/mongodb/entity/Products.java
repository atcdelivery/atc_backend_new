package com.atcdilivery.spring.jwt.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Products")
public class Products {
    @Id
    private String id;
    private String categoryId;
    private String userId;
    private String categoryName;
    private String productName;
    private String productCode;
    private double productPrice;
    private int productQuantity;
}