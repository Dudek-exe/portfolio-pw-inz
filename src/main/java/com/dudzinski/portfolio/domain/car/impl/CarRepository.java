package com.dudzinski.portfolio.domain.car.impl;

import com.dudzinski.portfolio.application.car.dto.CarSearchParamsDTO;
import com.dudzinski.portfolio.domain.car.CarEntity;
import com.dudzinski.portfolio.domain.car.CarEntity_;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
class CarRepository {

    private final CarJpaRepository carJpaRepository;

    public CarEntity save(CarEntity car) {
        return carJpaRepository.save(car);
    }

    Page<CarEntity> findAll(Specification<CarEntity> spec, Pageable pageable) {
        return carJpaRepository.findAll(spec, pageable);
    }

    public CarEntity getById(Long carId) {
        return carJpaRepository.findById(carId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Specification<CarEntity> buildSpecification(CarSearchParamsDTO dto) {
        return (root, query, cb) -> {
            Optional<Predicate> namePredicate = Optional.ofNullable(dto.getName())
                    .map(name -> cb.like(cb.lower(root.get(CarEntity_.name)), name.toLowerCase()));

            Optional<Predicate> brandPredicate = Optional.ofNullable(dto.getBrand())
                    .map(brand -> cb.like(cb.lower(root.get(CarEntity_.brand)), brand.toLowerCase()));

            List<Predicate> predicates = Stream.of(
                            namePredicate,
                            brandPredicate
                    )
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public void delete(Long carId) {
        carJpaRepository.deleteById(carId);
    }
}
