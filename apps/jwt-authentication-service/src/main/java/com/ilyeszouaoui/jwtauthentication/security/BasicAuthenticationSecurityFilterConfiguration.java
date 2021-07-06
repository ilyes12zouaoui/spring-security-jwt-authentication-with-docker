package com.ilyeszouaoui.jwtauthentication.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.stereotype.Component;

@Component
@Order(98)
public class BasicAuthenticationSecurityFilterConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${ilyes.basic-auth.credentials.username}")
    private String username;

    @Value("${ilyes.basic-auth.credentials.password}")
    private String password;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().requestMatchers().mvcMatchers("/api/internal/v1/**")
                .and().httpBasic().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.FORBIDDEN))
                .and().authorizeRequests().anyRequest().hasRole("INTERNAL");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(username).password(passwordEncoder.encode(password)).roles("INTERNAL");
    }
}
