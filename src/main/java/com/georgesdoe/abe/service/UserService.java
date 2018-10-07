package com.georgesdoe.abe.service;

import com.georgesdoe.abe.domain.User;
import com.georgesdoe.abe.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository repository;
    private PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder){
        this.repository = repository;
        this.encoder = encoder;
    }

    public User create(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));

        repository.save(user);

        return user;
    }
}
