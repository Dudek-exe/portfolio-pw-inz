package com.dudzinski.portfolio.domain.cryptocurrency.impl;

import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface CryptoCurrencyJpaRepository extends JpaRepository<CryptoCurrencyEntity, Long>, JpaSpecificationExecutor<CryptoCurrencyEntity> {
}
