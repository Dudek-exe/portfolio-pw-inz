package com.dudzinski.portfolio.domain.cryptocurrency.impl;

import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CryptoCurrencyJpaRepository extends JpaRepository<CryptoCurrencyEntity, Long> {

    Page<CryptoCurrencyEntity> findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(String name, String code, Pageable pageable);

    Page<CryptoCurrencyEntity> findAllByNameContainsIgnoreCase(String name, Pageable pageable);

    Page<CryptoCurrencyEntity> findAllByCodeContainsIgnoreCase(String code, Pageable pageable);

    Optional<CryptoCurrencyEntity> findById(Long id);

}
