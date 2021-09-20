package com.farukyilmaz.spring.boot.thymeleaf.crud.services;

import com.farukyilmaz.spring.boot.thymeleaf.crud.models.SystemUser;

import java.util.ArrayList;

public interface SystemUserService {
    ArrayList<SystemUser> getList();
    SystemUser findByEmailAndPassword(String email, String password);
    SystemUser save (SystemUser su);
    void delete(SystemUser su);
}
