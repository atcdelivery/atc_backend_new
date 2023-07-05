package com.atcdilivery.spring.jwt.mongodb.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WareHouseRequest {
    @NotNull
    private String userId;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    @Size(min = 5,max = 6)
    private String pin;

    @NotNull
    private boolean returnSame;
    @NotNull
    @Size(min = 10,max = 11)
    private String phone;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    @Email
    private String email;

    private String returnAddress;

    private String returnCity;

    private String returnState;

    private String returnPin;
}
