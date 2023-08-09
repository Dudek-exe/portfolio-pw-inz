package com.dudzinski.portfolio.domain.cryptocurrency.impl;

import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class CryptoCurrencyRepository {

    private final CryptoCurrencyJpaRepository cryptoCurrencyJpaRepository;

    void save(CryptoCurrencyEntity cryptoCurrencyEntity) {
        cryptoCurrencyJpaRepository.save(cryptoCurrencyEntity);
    }

    Page<CryptoCurrencyEntity> findAll(Pageable pageable) {
        return cryptoCurrencyJpaRepository.findAll(pageable);
    }

    Page<CryptoCurrencyEntity> findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(String name, String code, Pageable pageable) {
        return cryptoCurrencyJpaRepository.findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(name, code, pageable);
    }

    Page<CryptoCurrencyEntity> findAllByNameContainsIgnoreCase(String name, Pageable pageable) {
        return cryptoCurrencyJpaRepository.findAllByNameContainsIgnoreCase(name, pageable);
    }

    Page<CryptoCurrencyEntity> findAllByCodeContainsIgnoreCase(String code, Pageable pageable) {
        return cryptoCurrencyJpaRepository.findAllByCodeContainsIgnoreCase(code, pageable);
    }

}
