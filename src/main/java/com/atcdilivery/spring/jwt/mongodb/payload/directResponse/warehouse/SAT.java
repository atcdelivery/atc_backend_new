package com.atcdilivery.spring.jwt.mongodb.payload.directResponse.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SAT{
    public String start_time;
    public String close_time;
}
