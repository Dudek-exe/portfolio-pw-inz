package com.dudzinski.portfolio.domain.car.impl;

import com.dudzinski.portfolio.domain.car.CarEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CarRepository {

    private final CarJpaRepository carJpaRepository;

    List<CarEntity> findAllByBrand(String brand) {
        return carJpaRepository.findAllByBrand(brand);
    }

    public CarEntity save(CarEntity car) {
        return carJpaRepository.save(car);
    }

    public List<CarEntity> findAll() {
        return carJpaRepository.findAll();
    }
}
