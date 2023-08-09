package com.dudzinski.portfolio.domain.client;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityClientService extends UserDetailsService {

    CustomUserDetail findUserDetailsByUsername(String username);
}
