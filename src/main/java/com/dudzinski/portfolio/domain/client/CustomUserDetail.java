package com.dudzinski.portfolio.domain.client;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Getter
public class CustomUserDetail extends org.springframework.security.core.userdetails.User implements UserDetails {

    private final String externalId;

    public CustomUserDetail(String username, String password, Set<SimpleGrantedAuthority> authority, String externalId) {
        super(username, password, authority);
        this.externalId = externalId;
    }

}
