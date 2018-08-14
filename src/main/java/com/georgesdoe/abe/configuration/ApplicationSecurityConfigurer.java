package com.georgesdoe.abe.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.georgesdoe.abe.repository.UserRepository;
import com.georgesdoe.abe.security.JPAUserDetailsService;
import com.georgesdoe.abe.security.JWTAuthenticationProvider;
import com.georgesdoe.abe.security.JWTBearerFilter;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.io.UnsupportedEncodingException;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfigurer extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTAuthenticationProvider provider;

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .formLogin()
                    .disable()
                .cors()
                    .and()
                .csrf()
                    .disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .addFilter(new JWTBearerFilter(authenticationManager()))
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
    protected UserDetailsService userDetailsService(){
        return new JPAUserDetailsService(userRepository);
    }

    @Bean
    public JWTVerifier getVerifier() throws UnsupportedEncodingException {

        Algorithm algorithm = Algorithm.HMAC256("${app.jwt.secret}");

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("${app.jwt.issuer}")
                .build();

        return verifier;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
