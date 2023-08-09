package com.dudzinski.portfolio.infrastructure.config.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface TokenProvider {

    AuthToken generateToken(Authentication authentication);

    Boolean validateToken(String token, UserDetails userDetails);

    String getUsernameFromToken(String token);

    UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final UserDetails userDetails);

}
