package com.dudzinski.portfolio.domain.currency.impl;

import com.dudzinski.portfolio.domain.currency.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

interface CurrencyJpaRepository extends JpaRepository<CurrencyEntity, Long>, JpaSpecificationExecutor<CurrencyEntity> {

    @Query("SELECT c FROM CurrencyEntity c WHERE c.date = :date AND c.code = :code")
    Optional<CurrencyEntity> findByCodeAndDateIgnoreCase(String code, LocalDate date);
}
