package com.atcdilivery.spring.jwt.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "UserDocument")
public class CustomerDocument {

    @Id
    private String id;
    private String userId;
    private String documentType1;
    private String documentType2;
    private String documentType1FileName;
    private String documentType2FileName;
    private String path;
    private Date createDate;
    private Date modifyDate;
}
