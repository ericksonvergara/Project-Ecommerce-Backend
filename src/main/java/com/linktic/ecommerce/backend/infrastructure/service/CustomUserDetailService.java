package com.linktic.ecommerce.backend.infrastructure.service;

import com.linktic.ecommerce.backend.application.UserService;
import com.linktic.ecommerce.backend.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername: {}", username);
        User user = userService.findByEmail(username);
        return org.springframework.security.core.userdetails.User.builder().username(user.getEmail())
                .password(user.getPassword()).roles(user.getUserType().name()).build();
    }
}
