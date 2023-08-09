package com.dudzinski.portfolio.domain.cryptocurrency.impl;

import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
class CryptoCurrencyRepository {

    private final CryptoCurrencyJpaRepository cryptoCurrencyJpaRepository;

    CryptoCurrencyEntity save(CryptoCurrencyEntity cryptoCurrencyEntity) {
        return cryptoCurrencyJpaRepository.save(cryptoCurrencyEntity);
    }

    List<CryptoCurrencyEntity> findAll() {
        return cryptoCurrencyJpaRepository.findAll();
    }

    List<CryptoCurrencyEntity> findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(String name, String code) {
        return cryptoCurrencyJpaRepository.findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(name, code);
    }

    List<CryptoCurrencyEntity> findAllByNameContainsIgnoreCase(String name) {
        return cryptoCurrencyJpaRepository.findAllByNameContainsIgnoreCase(name);
    }

    List<CryptoCurrencyEntity> findAllByCodeContainsIgnoreCase(String code) {
        return cryptoCurrencyJpaRepository.findAllByCodeContainsIgnoreCase(code);
    }

}
