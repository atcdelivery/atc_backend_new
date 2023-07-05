package com.atcdilivery.spring.jwt.mongodb.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    public Boolean store(MultipartFile file1,MultipartFile file2,String documentName1,String documentName2);

}
