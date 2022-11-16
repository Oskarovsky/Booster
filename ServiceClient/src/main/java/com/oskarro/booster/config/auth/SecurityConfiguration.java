package com.oskarro.booster.config.auth;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    final SlykoUserDetailsService userDetailsService;

    private static final String[] AUTH_WHITELIST = {
            "/login",
            "/api/calc/**"
    };

    private static final String[] AUTH_ADMIN = {
            "/h2-console/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                    .antMatchers(AUTH_WHITELIST).permitAll()
                    .antMatchers(AUTH_ADMIN).hasAnyRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE).hasAnyRole("WRITE", "ADMIN")
                    .antMatchers(HttpMethod.POST).hasAnyRole("WRITE", "ADMIN")
                    .antMatchers(HttpMethod.PUT).hasAnyRole("WRITE", "ADMIN")
                    .antMatchers(HttpMethod.PATCH).hasAnyRole("WRITE", "ADMIN")
                    .antMatchers(HttpMethod.GET).hasAnyRole("READ", "WRITE", "ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .formLogin().permitAll()
                .and()
                    .logout().permitAll()
                .and()
                    .httpBasic();

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity
                .ignoring()
                .antMatchers("/h2-console/**");
    }
}
