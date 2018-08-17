package com.georgesdoe.abe.security;

import com.georgesdoe.abe.domain.User;
import com.georgesdoe.abe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

public class JPAUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public JPAUserDetailsService(UserRepository repository){
        this.userRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new JPAUser(user);
    }
}
