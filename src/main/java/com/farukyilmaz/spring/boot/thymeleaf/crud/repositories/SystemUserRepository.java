package com.farukyilmaz.spring.boot.thymeleaf.crud.repositories;

import com.farukyilmaz.spring.boot.thymeleaf.crud.models.SystemUser;
import org.springframework.data.repository.CrudRepository;

public interface SystemUserRepository extends CrudRepository<SystemUser,Long> {
    SystemUser findByEmailAndPassword(String email, String password);
    SystemUser findByUsername(String username);
}
