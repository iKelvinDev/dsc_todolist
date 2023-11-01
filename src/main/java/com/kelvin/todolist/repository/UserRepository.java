package com.kelvin.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.kelvin.todolist.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByLogin(String username);
    
}
