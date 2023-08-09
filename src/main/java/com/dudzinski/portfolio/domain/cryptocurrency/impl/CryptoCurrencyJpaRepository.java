package com.dudzinski.portfolio.domain.cryptocurrency.impl;

import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface CryptoCurrencyJpaRepository extends JpaRepository<CryptoCurrencyEntity, Long> {

    Optional<CryptoCurrencyEntity> findByCodeIgnoreCase(String code);

    List<CryptoCurrencyEntity> findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(String name, String code);

    List<CryptoCurrencyEntity> findAllByNameContainsIgnoreCase(String name);

    List<CryptoCurrencyEntity> findAllByCodeContainsIgnoreCase(String code);

    Optional<CryptoCurrencyEntity> findById(Long Id);

}
