package com.georgesdoe.sbjs.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.georgesdoe.sbjs.repository")
public class JPAConfigurer {
}
