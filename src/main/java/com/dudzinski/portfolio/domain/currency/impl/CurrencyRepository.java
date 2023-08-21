package com.dudzinski.portfolio.domain.currency.impl;

import com.dudzinski.portfolio.application.currency.dto.CurrencySearchParamsDTO;
import com.dudzinski.portfolio.domain.currency.CurrencyEntity;
import com.dudzinski.portfolio.domain.currency.CurrencyEntity_;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

    Page<CurrencyEntity> findAll(Specification<CurrencyEntity> spec, Pageable pageable) {
        return currencyJpaRepository.findAll(spec, pageable);
    }

    public Specification<CurrencyEntity> buildSpecification(CurrencySearchParamsDTO dto) {
        return (root, query, cb) -> {
            Optional<Predicate> codePredicate = Optional.ofNullable(dto.getCode())
                    .map(code -> cb.like(cb.lower(root.get(CurrencyEntity_.code)), code.toLowerCase()));

            Optional<Predicate> namePredicate = Optional.ofNullable(dto.getName())
                    .map(name -> cb.like(cb.lower(root.get(CurrencyEntity_.name)), name.toLowerCase()));

            Optional<Predicate> dateFromPredicate = Optional.ofNullable(dto.getDateFrom())
                    .map(dateFrom -> cb.greaterThanOrEqualTo(root.get(CurrencyEntity_.date), dateFrom));

            Optional<Predicate> dateToPredicate = Optional.ofNullable(dto.getDateTo())
                    .map(dateTo -> cb.lessThanOrEqualTo(root.get(CurrencyEntity_.date), dateTo));

            List<Predicate> predicates = Stream.of(
                            codePredicate,
                            namePredicate,
                            dateFromPredicate,
                            dateToPredicate
                    )
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
