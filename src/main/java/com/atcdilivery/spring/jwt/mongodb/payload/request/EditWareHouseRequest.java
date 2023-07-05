package com.atcdilivery.spring.jwt.mongodb.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditWareHouseRequest {
    @NotNull
    private String name;
    @NotNull
    private String pin;
    @NotNull
    private String address;
    @NotNull
    private String phone;
}
