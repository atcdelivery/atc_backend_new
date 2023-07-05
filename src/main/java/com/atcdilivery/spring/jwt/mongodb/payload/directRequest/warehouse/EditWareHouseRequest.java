package com.atcdilivery.spring.jwt.mongodb.payload.directRequest.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditWareHouseRequest {
  private String name;
  private String pin;
  private String registered_name;
  private String address;
  private String phone;
}
