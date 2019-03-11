package com.georgesdoe.sbjs.service;

import com.georgesdoe.sbjs.domain.User;
import com.georgesdoe.sbjs.repository.UserRepository;
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
