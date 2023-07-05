package com.atcdilivery.spring.jwt.mongodb.payload.directRequest.createorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
    private int pin;
    private String state;
    private String city;
    private String add;
    private String name;
    private Long phone;
    private String alternate_phone;
    private String order;
    private double total_amount;
    private String products_desc;
    private ExtraParameters extra_parameters;
    private String country;
    private String country_code;
    private String order_date;
//    private String client;
    private boolean fragile_shipment;
    private String source;
    private String payment_mode;
    private double weight;
    private String shipment_length;
    private String shipment_width;
    private String shipment_height;
    private double tax_value;
    private double commodity_value;
    private String category_of_goods;
    private String seller_name;
    private String seller_add;
    private String seller_inv_date;
    private double cod_amount;
}
