package com.dudzinski.portfolio.domain.client.impl;

import com.dudzinski.portfolio.domain.client.RoleEntity;
import com.dudzinski.portfolio.domain.client.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity getByName(RoleType name);

    Optional<RoleEntity> findByName(RoleType name);
}
