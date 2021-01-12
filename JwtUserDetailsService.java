package com.formation.formationspring.services;

import com.formation.formationspring.dao.IUserRepository;
import com.formation.formationspring.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    IUserRepository iUserRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User customer = iUserRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(email));

        List<SimpleGrantedAuthority> grantedAuthorities = customer.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getLabel())).collect(Collectors.toList()); // (1)
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(customer.getEmail()).password(customer.getPassword()).disabled(false)
                .authorities(grantedAuthorities).build();
        return userDetails;
    }

}
