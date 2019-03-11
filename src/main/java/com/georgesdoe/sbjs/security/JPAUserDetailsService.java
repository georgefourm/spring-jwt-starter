package com.georgesdoe.sbjs.security;

import com.georgesdoe.sbjs.domain.User;
import com.georgesdoe.sbjs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
