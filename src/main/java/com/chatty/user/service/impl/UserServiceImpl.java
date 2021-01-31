package com.chatty.user.service.impl;

import com.chatty.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SessionRegistry sessionRegistry;

    @Override
    public List<String> getLoggedInUserNames() {
        return sessionRegistry.getAllPrincipals().stream()
                .filter(p -> p instanceof User)
                .map(p -> ((User)p).getUsername())
                .collect(Collectors.toList());
    }

    @Override
    public String getCurrentLoggedInUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal instanceof User ? ((User)principal).getUsername() : principal.toString();
    }
}
