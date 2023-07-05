package com.atcdilivery.spring.jwt.mongodb.controllers;

import com.atcdilivery.spring.jwt.mongodb.service.StorageService;
import com.atcdilivery.spring.jwt.mongodb.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class VerificationController {

    @Autowired
    VerificationService verificationService;

    @PostMapping("/verification-docs")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String getUserVerificationDocs(@RequestParam MultipartFile document1,@RequestParam MultipartFile document2,@RequestParam String documentType1,@RequestParam String documentType2,@RequestParam String userId){
        String response = verificationService.saveCustomerDocument(document1,document2,documentType1,documentType2,userId);
        return response;
    }

    @PostMapping("/verify-user")
    @PreAuthorize("hasRole('ADMIN')")
    public String verifyUser(@RequestParam String userId){
        return verificationService.verifyCustomer(userId);
    }




}
