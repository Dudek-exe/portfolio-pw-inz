package com.dudzinski.portfolio.domain.client.impl;

import com.dudzinski.portfolio.domain.client.ClientEntity;
import com.dudzinski.portfolio.domain.client.CustomUserDetail;
import com.dudzinski.portfolio.domain.client.SecurityClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityClientServiceImpl implements SecurityClientService {

    private final ClientRepository clientRepository;

    @Override
    public CustomUserDetail findUserByUsername(String username) {
        return loadUserByUsername(username);
    }

    @Override
    public CustomUserDetail loadUserByUsername(String s) throws UsernameNotFoundException {
        ClientEntity clientEntity = clientRepository.findByLogin(s).orElseThrow(() -> new IllegalArgumentException("Something went wrong"));

        return new CustomUserDetail(clientEntity.getLogin(), clientEntity.getPassword(), getAuthority(clientEntity), clientEntity.getExternalId());
    }

    private Set<SimpleGrantedAuthority> getAuthority(ClientEntity clientEntity) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        clientEntity.getRoleEntities().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return authorities;
    }
}
