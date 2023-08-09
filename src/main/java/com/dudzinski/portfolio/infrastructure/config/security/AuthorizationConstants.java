package com.dudzinski.portfolio.infrastructure.config.security;

public class AuthorizationConstants {
    public static final String SIGNING_KEY = "portfolio2503111111111111111111111111111111111111111111111111111111111111";
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "JWT=";

    public static final String AUTHORITIES_KEY = "scopes";
    private static final long ACCESS_TOKEN_VALIDITY_SECONDS = 24 * 60 * 60;
    private static final long VALIDITY_NUMBER_OF_DAYS = 1;
    public static final long ACCESS_TOKEN_VALIDITY = ACCESS_TOKEN_VALIDITY_SECONDS * VALIDITY_NUMBER_OF_DAYS;

    private AuthorizationConstants() {
    }
}
