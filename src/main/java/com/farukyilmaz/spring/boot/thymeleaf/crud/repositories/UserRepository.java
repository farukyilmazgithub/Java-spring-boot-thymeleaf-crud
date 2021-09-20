package com.farukyilmaz.spring.boot.thymeleaf.crud.repositories;

import com.farukyilmaz.spring.boot.thymeleaf.crud.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long>{
}
