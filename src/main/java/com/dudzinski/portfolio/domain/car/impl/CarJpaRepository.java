package com.dudzinski.portfolio.domain.car.impl;

import com.dudzinski.portfolio.domain.car.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface CarJpaRepository extends JpaRepository<CarEntity, Long> {

    List<CarEntity> findAllByBrand(String brand);
}
