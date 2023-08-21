package com.dudzinski.portfolio.domain.car.impl;

import com.dudzinski.portfolio.domain.car.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
interface CarJpaRepository extends JpaRepository<CarEntity, Long>, JpaSpecificationExecutor<CarEntity> {
}
