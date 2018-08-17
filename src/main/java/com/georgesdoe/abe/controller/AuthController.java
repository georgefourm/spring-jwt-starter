package com.georgesdoe.abe.controller;

import com.georgesdoe.abe.domain.User;
import com.georgesdoe.abe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/account")
    public User getAccount(@AuthenticationPrincipal User user){
        return user;
    }
}
