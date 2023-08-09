package com.dudzinski.portfolio.domain.client.impl;

import com.dudzinski.portfolio.domain.client.RoleEntity;
import com.dudzinski.portfolio.domain.client.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepository {

    private final RoleJpaRepository roleJpaRepository;

    public RoleEntity getByName(RoleType name) {
        return roleJpaRepository.getByName(name);
    }

    public Optional<RoleEntity> findByName(RoleType name) {
        return roleJpaRepository.findByName(name);
    }


    public RoleEntity save(RoleEntity role) {
        return roleJpaRepository.save(role);
    }


}