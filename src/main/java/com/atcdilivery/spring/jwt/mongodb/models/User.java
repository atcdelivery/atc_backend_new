package com.atcdilivery.spring.jwt.mongodb.models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.atcdilivery.spring.jwt.mongodb.entity.Warehouse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User {

  @Transient
  public static final String SEQUENCE_NAME = "user_sequence";

  @Id
  private String id;
  @NotBlank
  @Size(max = 20)
  private String username;
  @NotBlank
  @Size(max = 50)
  @Email
  private String email;
  @NotBlank
  @Size(max = 120)
  private String password;
  @DBRef
  private Set<Role> roles = new HashSet<>();

  private String firstName;
  private String lastName;
  private Date createDate;
  private Date modifyDate;
  private boolean verified;

//  New Felids
  private String userId;
  private List<Warehouse> warehouse;



  private boolean emailVerified;
  public User(String username, String email, String password,Date createDate,Date modifyDate,Boolean verified,Boolean emailVerified,String firstName,String lastName) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.createDate = createDate;
    this.modifyDate = modifyDate;
    this.verified = verified;
    this.emailVerified = emailVerified;
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
