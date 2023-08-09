package com.dudzinski.portfolio.domain.client.dto;

import com.dudzinski.portfolio.infrastructure.config.security.token.AuthToken;
import lombok.Getter;

import java.util.Date;

@Getter
public class LoginResponseDTO {

    private final String token;
    private final Date expirationTime;

    public LoginResponseDTO(AuthToken token) {
        this.token = token.getToken();
        this.expirationTime = token.getExpirationTime();
    }

}
