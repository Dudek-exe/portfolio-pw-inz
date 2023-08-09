package com.dudzinski.portfolio.domain.currency.impl;

import com.dudzinski.portfolio.domain.currency.CurrencyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

interface CurrencyJpaRepository extends JpaRepository<CurrencyEntity, Long> {

    Page<CurrencyEntity> findAllByCodeContainsIgnoreCase(String code, Pageable pageable);

    Page<CurrencyEntity> findByNameContainsIgnoreCase(String name, Pageable pageable);

    Page<CurrencyEntity> findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(String name, String code, Pageable pageable);

    @Query("SELECT c FROM CurrencyEntity c WHERE c.date = :date AND c.code = :code")
    Optional<CurrencyEntity> findByCodeAndDateIgnoreCase(String code, LocalDate date);
}
