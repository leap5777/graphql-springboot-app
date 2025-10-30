package com.logwin.graphql.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String,String> users = Map.of(
            "user","$2a$10$.IfyVM1O0eEYaBmIlojl8.CioPlei8kqhjBe2Y9Tn6nHnBQQDT.oG", // pass1
            "admin", "$2a$10$mSxbm/Jbf1gS7WnLjmqu2enz.T1KIAL9qUHcdoFgDXpNv1NDchTCW" // admin
    );


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!users.containsKey(username)) throw new UsernameNotFoundException("User: " + username + " not present ");

        return User
                .withUsername(username)
                .password(users.get(username))
                .roles(username)
                .build();
    }
}
