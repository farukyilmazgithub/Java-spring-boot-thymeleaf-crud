package com.farukyilmaz.spring.boot.thymeleaf.crud.models;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemUser {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long Id;
    @NotEmpty(message = "Username required")
    private String username;
    @NotEmpty(message = "Name required")
    private String name;
    @NotEmpty(message = "Surname required")
    private String surName;
    @NotEmpty(message = "Email required")
    private String email;
    @NotNull(message = "Password required")
    private String password;
    @Transient
    private String passwordConfirm;
}