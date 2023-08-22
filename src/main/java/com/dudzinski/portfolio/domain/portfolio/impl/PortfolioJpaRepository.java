package com.dudzinski.portfolio.domain.portfolio.impl;

import com.dudzinski.portfolio.domain.portfolio.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
interface PortfolioJpaRepository extends JpaRepository<PortfolioEntity, Long>, JpaSpecificationExecutor<PortfolioEntity> {
}
