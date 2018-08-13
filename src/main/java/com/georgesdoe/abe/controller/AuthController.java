package com.georgesdoe.abe.controller;

import com.georgesdoe.abe.domain.User;
import com.georgesdoe.abe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/account")
    public User getAccount(@RequestParam(name = "username",required = true) String username){
        return userRepository.findByUsername(username)
                .get();
    }
}
