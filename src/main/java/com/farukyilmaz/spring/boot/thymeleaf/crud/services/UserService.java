package com.farukyilmaz.spring.boot.thymeleaf.crud.services;

import com.farukyilmaz.spring.boot.thymeleaf.crud.models.User;

import java.util.ArrayList;

public interface UserService {
    ArrayList<User> getList();
    User findById(Long Id);
    User save (User u);
    void delete(User u);
}
