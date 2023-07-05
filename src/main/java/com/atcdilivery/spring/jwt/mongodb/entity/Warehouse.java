package com.atcdilivery.spring.jwt.mongodb.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Warehouse")
public class Warehouse {
    @Id
    private String id;
    private String userId;
    private String name;
    private String address;
    private String pin;
    private String phone;
    private boolean returnSame;
    private String city;
    private String state;
    private String email;
    private String returnAddress;
    private String returnCity;
    private String returnState;
    private String returnPin;
}
