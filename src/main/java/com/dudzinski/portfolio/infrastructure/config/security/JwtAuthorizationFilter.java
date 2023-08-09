package com.dudzinski.portfolio.infrastructure.config.security;

import com.dudzinski.portfolio.domain.client.CustomUserDetail;
import com.dudzinski.portfolio.domain.client.SecurityClientService;
import com.dudzinski.portfolio.infrastructure.config.security.token.TokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@AllArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private SecurityClientService securityClientService;
    private TokenProvider tokenProviderImpl;

    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(AuthorizationConstants.HEADER_STRING);
        String username = null;
        String authToken = null;

        if (Objects.nonNull(header) && header.startsWith(AuthorizationConstants.TOKEN_PREFIX)) {
            authToken = header.replace(AuthorizationConstants.TOKEN_PREFIX, "");
            try {
                username = tokenProviderImpl.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("an error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore", e);
            } catch (SignatureException e) {
                logger.error("Authentication Failed. Username or Password not valid.");
            }
        }

        if (Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {

            CustomUserDetail userDetail = securityClientService.findUserByUsername(username);

            if (Boolean.TRUE.equals(tokenProviderImpl.validateToken(authToken, userDetail))) {
                UsernamePasswordAuthenticationToken authentication = tokenProviderImpl.getAuthentication(
                        authToken,
                        SecurityContextHolder.getContext().getAuthentication(),
                        userDetail
                );

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

                logger.info("authenticated user with id: " + userDetail.getExternalId() + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(req, res);
    }
}

