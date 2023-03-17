package com.demo.project.security;

import com.demo.project.entity.RoleEntity;
import com.demo.project.entity.UserEntity;
import com.demo.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        return new User(user.getEmail(), user.getPassword(), mapRoleToAuthorities(user.getRole()));
    }

    private Collection<GrantedAuthority> mapRoleToAuthorities(RoleEntity role) {
        return Collections.singleton(new SimpleGrantedAuthority(role.getName()));
    }
}
