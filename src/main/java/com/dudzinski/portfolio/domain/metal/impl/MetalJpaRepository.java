package com.dudzinski.portfolio.domain.metal.impl;

import com.dudzinski.portfolio.domain.metal.MetalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface MetalJpaRepository extends JpaRepository<MetalEntity, Long> {

    Optional<MetalEntity> findByNameIgnoreCase(String code);

}
