package com.oskarro.booster.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration // (1)
@EnableWebSecurity // (2)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception { // (3)
        httpSecurity
                .authorizeRequests()
                    .antMatchers("/**", "/index").permitAll() // (4)
                    .anyRequest().authenticated() // (5)
                .and()
                    .formLogin()// (6)
                    .loginPage("/login").permitAll() // (7)
                .and()
                    .logout().permitAll() // (8)
                .and()
                    .httpBasic(); // (9)
    }
}
