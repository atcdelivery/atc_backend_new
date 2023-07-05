package com.atcdilivery.spring.jwt.mongodb.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private int statusCode;
    private String message;
    private String additionInformation;
}
