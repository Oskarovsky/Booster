package com.oskarro.booster.config.auth;

import com.oskarro.apiclient.model.auth.UserMan;
import com.oskarro.booster.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SlykoUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public SlykoUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserMan userMan = userRepository.findByUsername(username);
        if (userMan == null) {
            throw new UsernameNotFoundException(username);
        }
        return new Principal(userMan);
    }
}
