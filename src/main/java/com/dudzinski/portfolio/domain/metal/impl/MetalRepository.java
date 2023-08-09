package com.dudzinski.portfolio.domain.metal.impl;

import com.dudzinski.portfolio.domain.metal.MetalEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MetalRepository {

    private final MetalJpaRepository metalJpaRepository;

    MetalEntity save(MetalEntity metalEntity) {
        return metalJpaRepository.save(metalEntity);
    }

    List<MetalEntity> findAll() {
        return metalJpaRepository.findAll();
    }

    Optional<MetalEntity> findByNameIgnoreCase(String code) {
        return metalJpaRepository.findByNameIgnoreCase(code);
    }
}
