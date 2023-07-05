package com.atcdilivery.spring.jwt.mongodb.service;

import org.springframework.web.multipart.MultipartFile;

public interface VerificationService {

    String saveCustomerDocument(MultipartFile document1, MultipartFile document2, String documentType1, String documentType2,String userdId);

    String verifyCustomer(String userId);
}
