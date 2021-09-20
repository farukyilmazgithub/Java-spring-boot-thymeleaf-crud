package com.farukyilmaz.spring.boot.thymeleaf.crud.services;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String username, String password);
}
