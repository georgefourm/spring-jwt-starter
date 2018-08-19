package com.georgesdoe.abe.configuration;

import com.georgesdoe.abe.configuration.jwt.JWTManager;
import com.georgesdoe.abe.configuration.jwt.JWTProperties;
import com.georgesdoe.abe.repository.UserRepository;
import com.georgesdoe.abe.security.JPAUserDetailsService;
import com.georgesdoe.abe.security.JWTAuthenticationFilter;
import com.georgesdoe.abe.security.JWTAuthenticationProvider;
import com.georgesdoe.abe.security.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(JWTProperties.class)
public class ApplicationSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTAuthenticationProvider provider;

    @Autowired
    private JWTManager jwtManager;

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .formLogin()
                    .disable()
                .logout()
                    .disable()
                .cors()
                    .and()
                .csrf()
                    .disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(),jwtManager))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .authorizeRequests()
                    .anyRequest()
                    .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider)
                .userDetailsService(userDetailsService())
                .passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return new JPAUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
