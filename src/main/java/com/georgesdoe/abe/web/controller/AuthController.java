package com.georgesdoe.abe.web.controller;

import com.georgesdoe.abe.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/account")
    public User getAccount(@AuthenticationPrincipal User user){
        return user;
    }
}
