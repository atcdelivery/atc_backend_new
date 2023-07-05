package com.atcdilivery.spring.jwt.mongodb.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginEmailRequest {

    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String email;
}
