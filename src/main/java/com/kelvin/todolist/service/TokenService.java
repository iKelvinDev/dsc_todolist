package com.kelvin.todolist.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.kelvin.todolist.domain.user.User;

@Service
public class TokenService {

  @Value("${api.todolist.token.secret}")
  private String seacret;

  public String generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(seacret);
      return JWT.create()
          .withIssuer("API todolist")
          .withSubject(user.getUsername())
          .withClaim("id", user.getId())
          .withExpiresAt(expirationDate())
          .sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new RuntimeException("Error generating token", exception);
    }
  }

  public String getSubject(String tokenJWT) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(seacret);
      return JWT.require(algorithm)
          // specify an specific claim validations
          .withIssuer("API todolist")
          // reusable verifier instance
          .build().verify(tokenJWT).getSubject();

    } catch (JWTVerificationException exception) {
      throw new RuntimeException("Invalid or expired token");
    }
  }

  private Instant expirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}
