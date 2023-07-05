package com.atcdilivery.spring.jwt.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ProductCategory")
public class ProductCategory {

    @Id
    private String id;
    private String categoryName;
    private Date createDate;
    private Date modifyDate;
}
