package com.dudzinski.portfolio.domain.client.impl;

import com.dudzinski.portfolio.domain.client.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface ClientJpaRepository extends JpaRepository<ClientEntity, Long> {

    ClientEntity findByExternalId(String externalId);

    Optional<ClientEntity> findByLogin(String login);

}
