package com.farukyilmaz.spring.boot.thymeleaf.crud.services;

import com.farukyilmaz.spring.boot.thymeleaf.crud.models.User;
import com.farukyilmaz.spring.boot.thymeleaf.crud.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {this.userRepository = userRepository;}

    @Override
    public ArrayList<User> getList() {
        ArrayList<User> userList =new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(userList::add);
        userList.sort(Comparator.comparing(User::getUserCode));
        return userList;
    }

    @Override
    public User findById(Long Id) {
        return userRepository.findById(Id).get();
    }

    @Override
    public User save(User u) {
        return userRepository.save(u);
    }

    @Override
    public void delete(User u) {
        userRepository.delete(u);
    }

}
