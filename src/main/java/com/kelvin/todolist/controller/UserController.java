package com.kelvin.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.kelvin.todolist.domain.user.User;
import com.kelvin.todolist.repository.UserRepository;
import jakarta.validation.Valid;
import com.kelvin.todolist.service.TokenService;

import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("users")

public class UserController {
    
    @Autowired
    private UserRepository repository;

    @Autowired
    TokenService tokenService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder){
        String passwordEncrypted = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncrypted);
        User userLocal = repository.save(user);
        String token = tokenService.generateToken(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(userLocal.getId()).toUri();
        return ResponseEntity.created(uri).headers(headers).build();

    }
}
