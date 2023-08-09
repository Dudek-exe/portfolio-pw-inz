package com.dudzinski.portfolio.domain.currency.impl;

import com.dudzinski.portfolio.domain.currency.CurrencyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class CurrencyRepository {

    private final CurrencyJpaRepository currencyJpaRepository;

    void save(CurrencyEntity currencyEntity) {
        currencyJpaRepository.save(currencyEntity);
    }

    Optional<CurrencyEntity> findByCodeIgnoreCase(String code, LocalDate date) {
        return currencyJpaRepository.findByCodeAndDateIgnoreCase(code, date);
    }

    Page<CurrencyEntity> findAll(Pageable pageable) {
        return currencyJpaRepository.findAll(pageable);
    }

    Page<CurrencyEntity> findAllByCodeContainsIgnoreCase(String code, Pageable pageable) {
        return currencyJpaRepository.findAllByCodeContainsIgnoreCase(code, pageable);
    }

    Page<CurrencyEntity> findByNameContainsIgnoreCase(String name, Pageable pageable) {
        return currencyJpaRepository.findByNameContainsIgnoreCase(name, pageable);
    }

    Page<CurrencyEntity> findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(String name, String code, Pageable pageable) {
        return currencyJpaRepository.findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(name, code, pageable);
    }

}
