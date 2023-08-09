package com.dudzinski.portfolio.domain.cryptocurrency.impl;

import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CryptoCurrencyRepository {

    private final CryptoCurrencyJpaRepository cryptoCurrencyJpaRepository;

    CryptoCurrencyEntity save(CryptoCurrencyEntity cryptoCurrencyEntity) {
        return cryptoCurrencyJpaRepository.save(cryptoCurrencyEntity);
    }

    List<CryptoCurrencyEntity> findAll() {
        return cryptoCurrencyJpaRepository.findAll();
    }

    Optional<CryptoCurrencyEntity> findByCodeIgnoreCase(String code) {
        return cryptoCurrencyJpaRepository.findByCodeIgnoreCase(code);
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

    Optional<CryptoCurrencyEntity> findById(Long id) {
        return cryptoCurrencyJpaRepository.findById(id);
    }

}
