package com.ilyeszouaoui.jwtauthentication.security;


import com.ilyeszouaoui.jwtauthentication.security.jwt.JwtSecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@Order(99)
public class JwtSecurityFilterConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtSecurityFilter jwtSecurityFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().requestMatchers()
                .and().authorizeRequests().anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {

        web.ignoring().antMatchers(
                "/api/app/v1/users/login",
                "/api/app/v1/users/register"
        );
    }
}
