package com.farukyilmaz.spring.boot.thymeleaf.crud.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail {
    private String mail;
    private String text;
    private String subject;
}
