package com.kelvin.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kelvin.todolist.domain.user.AuthenticationData;
import com.kelvin.todolist.domain.user.User;
import com.kelvin.todolist.infra.security.TokenDataJWT;
import com.kelvin.todolist.service.TokenService;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
  
  @Autowired
  private AuthenticationManager manager;

  
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  

  @Autowired
  TokenService tokenService;

  @PostMapping
  public ResponseEntity<Object> establishLogin(@RequestBody AuthenticationData data){
    var token = new UsernamePasswordAuthenticationToken(data.username(), data.password());
    var authentication = manager.authenticate(token);
    var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
    return ResponseEntity.ok(new TokenDataJWT(tokenJWT));
  }

   
  @GetMapping
  public ResponseEntity<String> getPasswordBcrypt(@RequestBody String password){
    String passwordBrypt = bCryptPasswordEncoder.encode(password);
    return ResponseEntity.ok(passwordBrypt);

  }

}
