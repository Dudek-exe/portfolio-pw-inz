package com.dudzinski.portfolio.domain.client.impl;

import com.dudzinski.portfolio.domain.client.ClientEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClientRepository {

    private final ClientJpaRepository clientJpaRepository;

    public Optional<ClientEntity> findByLogin(String login) {
        return clientJpaRepository.findByLogin(login);
    }

    public ClientEntity save(ClientEntity client) {
        return clientJpaRepository.save(client);
    }

    public ClientEntity findByExternalId(String externalId) {
        return clientJpaRepository.findByExternalId(externalId);
    }

}
