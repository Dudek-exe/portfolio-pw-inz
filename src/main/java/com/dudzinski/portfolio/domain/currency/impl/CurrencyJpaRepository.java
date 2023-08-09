package com.dudzinski.portfolio.domain.currency.impl;

import com.dudzinski.portfolio.domain.currency.CurrencyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CurrencyJpaRepository extends JpaRepository<CurrencyEntity, Long> {

    Page<CurrencyEntity> findAllByCodeContainsIgnoreCase(String code, Pageable pageable);

    Page<CurrencyEntity> findByNameContainsIgnoreCase(String name, Pageable pageable);

    Page<CurrencyEntity> findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(String name, String code, Pageable pageable);

    Optional<CurrencyEntity> findByCodeIgnoreCase(String code);
}
