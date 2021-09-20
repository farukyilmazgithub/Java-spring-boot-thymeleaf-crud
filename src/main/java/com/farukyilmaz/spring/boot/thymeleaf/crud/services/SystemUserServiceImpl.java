package com.farukyilmaz.spring.boot.thymeleaf.crud.services;

import com.farukyilmaz.spring.boot.thymeleaf.crud.repositories.SystemUserRepository;
import com.farukyilmaz.spring.boot.thymeleaf.crud.models.SystemUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final SystemUserRepository systemUserRepository;

    public SystemUserServiceImpl(SystemUserRepository systemUserRepository) {this.systemUserRepository = systemUserRepository;}

    @Override
    public ArrayList<SystemUser> getList() {
        ArrayList<SystemUser> systemUserList =new ArrayList<>();
        systemUserRepository.findAll().iterator().forEachRemaining(systemUserList::add);
        systemUserList.sort(Comparator.comparing(SystemUser::getId));
        return systemUserList;
    }

    @Override
    public SystemUser findByEmailAndPassword(String email, String password) {
        return systemUserRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public SystemUser save(SystemUser su) {
        su.setPassword(bCryptPasswordEncoder.encode(su.getPassword()));
        return systemUserRepository.save(su);
    }

    @Override
    public void delete(SystemUser su) {
        systemUserRepository.delete(su);
    }

/*
    @PostConstruct
    public void initData(){
        SystemUser systemUser = new SystemUser();
        systemUser.setUsername("admin");
        systemUser.setPassword("password");
        systemUser.setEmail("a");
        systemUser.setPasswordConfirm("password");
        systemUser.setName("a");
        systemUser.setSurName("a");
        systemUserRepository.save(systemUser);
    }
 */
}
