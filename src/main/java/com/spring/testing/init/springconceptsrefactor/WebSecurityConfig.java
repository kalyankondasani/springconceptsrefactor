package com.spring.testing.init.springconceptsrefactor;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // only the endpoint with this path "/api/refactor/v3/testing" is not authenticated.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers(request -> {

                    if (request.getRequestURI().contains("/api/refactor/v1/testing")) {
                        return false;
                    }
                    return true;
                }).authenticated()
                .and()
                .httpBasic();
    }
}
