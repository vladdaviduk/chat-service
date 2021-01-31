package com.chatty.user.service.impl;

import com.chatty.user.service.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserValidationServiceImpl implements UserValidationService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void validateUser(String username) {
        userDetailsService.loadUserByUsername(username);
    }
}
