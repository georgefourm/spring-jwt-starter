package com.georgesdoe.sbjs.configuration;

import com.georgesdoe.sbjs.repository.UserRepository;
import com.georgesdoe.sbjs.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SuperUserSeeder {

    private UserRepository repository;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private UserService service;

    @Value("${app.superuser.username}")
    private String username;

    @Value("${app.superuser.password}")
    private String password;

    @Autowired
    public SuperUserSeeder(UserRepository repository, UserService service){
        this.repository = repository;
        this.service = service;
    }

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event){
        long userCount = repository.count();
        if (userCount > 0){
            logger.info(String.format("Found %d user(s), skipping superuser creation",userCount));
            return;
        }
        logger.info("No users found, creating superuser...");

        service.create(username,password);

        logger.info("Created superuser");
    }
}
