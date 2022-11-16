package com.oskarro.booster.config.auth;

import com.oskarro.apiclient.model.auth.Role;
import com.oskarro.apiclient.model.auth.UserMan;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class Principal implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    private final UserMan userMan;

    private Collection<? extends GrantedAuthority> authorities;

    public Principal(UserMan user, Collection<? extends GrantedAuthority> authorities) {
        this.userMan = user;
        this.authorities = authorities;
    }

    public Principal(UserMan user) {
        this.userMan = user;
    }

    public static Principal build(UserMan userMan) {
        List<GrantedAuthority> authorities = userMan
                .getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new Principal(userMan, authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = userMan.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return userMan.getPassword();
    }

    @Override
    public String getUsername() {
        return userMan.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
