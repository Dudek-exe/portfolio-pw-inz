package com.dudzinski.portfolio.domain.client.impl;

import com.dudzinski.portfolio.domain.client.ClientEntity;
import jakarta.persistence.EntityExistsException;
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

    public ClientEntity getByLogin(String login) {
        return clientJpaRepository.findByLogin(login).orElseThrow(EntityExistsException::new);
    }

    public ClientEntity save(ClientEntity client) {
        return clientJpaRepository.save(client);
    }

}
