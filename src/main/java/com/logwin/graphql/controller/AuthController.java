package com.logwin.graphql.controller;

import com.logwin.graphql.service.CustomUserDetailsService;
import com.logwin.graphql.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    private CustomUserDetailsService customUserDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,CustomUserDetailsService customUserDetailsService){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping("/token")
    public Map<String,String> generateToken(@RequestBody Map<String,String> requestData){
        try{
            String username = requestData.get("username");
            String password = requestData.get("password");

            customUserDetailsService.loadUserByUsername(username);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)
            );
            String jwtToken = jwtUtil.generateToken(username);
            return Map.of("token",jwtToken);
        }
        catch (AuthenticationException ex){
            return Map.of("error","Invalid Token");
        }
    }
}
