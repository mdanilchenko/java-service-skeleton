package com.gfg.serviceskeleton.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JwtUserService implements UserDetailsService {

    private BCryptPasswordEncoder encoder;

    public JwtUserService() {
        this.encoder = new BCryptPasswordEncoder();
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.gfg.serviceskeleton.security.entity.User user;
        List<String> roles = new ArrayList<>();

        if ("ServiceUser".equals(username)) {
            roles.add("USER");
            user = new com.gfg.serviceskeleton.security.entity.User(
                    "ServiceUser",
                    encoder.encode("password123"),
                    roles
            );
        } else if ("ServiceAdmin".equals(username)) {
            roles.add("ADMIN");
            user = new com.gfg.serviceskeleton.security.entity.User(
                    "ServiceAdmin",
                    encoder.encode("password123"),
                    roles
            );

        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new User(user.getLogin(), user.getPasswoer(), getAuthority(user));
    }

    private Set<GrantedAuthority> getAuthority(com.gfg.serviceskeleton.security.entity.User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        });

        return authorities;
    }
}
