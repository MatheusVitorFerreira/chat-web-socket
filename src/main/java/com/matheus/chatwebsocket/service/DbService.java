package com.matheus.chatwebsocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.matheus.chatwebsocket.model.UserRole;
import com.matheus.chatwebsocket.model.Users;
import com.matheus.chatwebsocket.repository.UsersRepository;

@Service
public class DbService {

    @Autowired
    UsersRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void instantiateDataBase() {
        String encodedPassword = passwordEncoder.encode("123");
        Users user1 = new Users("matheus", encodedPassword, UserRole.ADMIN);
        userRepository.save(user1);
    }
}
