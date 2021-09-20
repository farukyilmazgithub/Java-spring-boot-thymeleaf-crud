package com.farukyilmaz.spring.boot.thymeleaf.crud.services;

import com.farukyilmaz.spring.boot.thymeleaf.crud.models.SystemUser;
import com.farukyilmaz.spring.boot.thymeleaf.crud.repositories.SystemUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class SystemUserDetailsServiceImpl implements UserDetailsService{

    private final SystemUserRepository systemUserRepository;

    public SystemUserDetailsServiceImpl(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        SystemUser systemUser = systemUserRepository.findByUsername(username);
        if (systemUser == null) throw new UsernameNotFoundException(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("System User"));
        return new org.springframework.security.core.userdetails.User(systemUser.getUsername(),
                new BCryptPasswordEncoder().encode(systemUser.getPassword()), grantedAuthorities);
    }
}
