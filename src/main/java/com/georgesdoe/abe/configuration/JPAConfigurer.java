package com.georgesdoe.abe.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.georgesdoe.abe.repository")
public class JPAConfigurer {
}
