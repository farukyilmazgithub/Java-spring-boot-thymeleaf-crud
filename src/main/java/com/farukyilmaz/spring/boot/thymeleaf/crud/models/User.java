package com.farukyilmaz.spring.boot.thymeleaf.crud.models;

import lombok.*;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long Id;
    @NotNull(message = "User code required")
    private Long userCode;
    @NotEmpty(message = "Name required")
    private String name;
    @NotEmpty(message = "Surname required")
    private String surName;
    @NotNull(message = "Phone number required")
    private Long phoneNumber;
    @NotEmpty(message = "Email required")
    private String email;
    @NotEmpty(message = "Account type required")
    private String accountType;
    @NotNull(message = "Account code required")
    private Long accountCode;
    @NotNull(message = "Balance required")
    private Double balance;
    private byte[] image;
    public String base64EncodedImage(){return Base64.encodeBase64String(getImage());}
}