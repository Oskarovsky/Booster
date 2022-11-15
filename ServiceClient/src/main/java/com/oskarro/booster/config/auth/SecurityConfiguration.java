package com.oskarro.booster.config.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration // (1)
@EnableWebSecurity // (2)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    final SlykoUserDetailsService userDetailsService;

    private static final String[] AUTH_WHITELIST = {
            "/h2-console/**",
            "/login"
    };

    public SecurityConfiguration(SlykoUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    @Override
//      public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception { // (3)
        httpSecurity
                .authorizeRequests()
//                    .antMatchers(AUTH_WHITELIST).permitAll()
//                    .antMatchers("/**", "/index").permitAll() // (4)
                    .anyRequest().authenticated() // (5)
                .and()
                    .formLogin() // (6)
//                    .loginPage("/login") // (7)
                .and()
                    .logout().permitAll() // (8)
                .and()
                    .httpBasic(); // (9)

        httpSecurity.csrf().disable(); // (10)
        httpSecurity.headers().frameOptions().disable(); // (11)
    }

    @Override
    public void configure(WebSecurity weSecurity) throws Exception {
        weSecurity
                .ignoring()
                .antMatchers("/h2-console/**");
    }
}
