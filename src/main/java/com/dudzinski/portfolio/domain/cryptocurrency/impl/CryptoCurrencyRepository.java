package com.dudzinski.portfolio.domain.cryptocurrency.impl;

import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyDetailsParamsDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencySearchParamsDTO;
import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyEntity;
import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyEntity_;
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
class CryptoCurrencyRepository {

    private final CryptoCurrencyJpaRepository cryptoCurrencyJpaRepository;

    void save(CryptoCurrencyEntity cryptoCurrencyEntity) {
        cryptoCurrencyJpaRepository.save(cryptoCurrencyEntity);
    }

    Page<CryptoCurrencyEntity> findAll(Specification<CryptoCurrencyEntity> spec, Pageable pageable) {
        return cryptoCurrencyJpaRepository.findAll(spec, pageable);
    }

    List<CryptoCurrencyEntity> findAll(Specification<CryptoCurrencyEntity> spec) {
        return cryptoCurrencyJpaRepository.findAll(spec);
    }

    public Specification<CryptoCurrencyEntity> buildSpecification(CryptoCurrencySearchParamsDTO dto) {
        return (root, query, cb) -> {
            Optional<Predicate> codePredicate = Optional.ofNullable(dto.getCode())
                    .map(code -> cb.like(cb.lower(root.get(CryptoCurrencyEntity_.code)), code.toLowerCase()));

            Optional<Predicate> namePredicate = Optional.ofNullable(dto.getName())
                    .map(name -> cb.like(cb.lower(root.get(CryptoCurrencyEntity_.name)), name.toLowerCase()));

            Optional<Predicate> dateFromPredicate = Optional.ofNullable(dto.getDateFrom())
                    .map(dateFrom -> cb.greaterThanOrEqualTo(root.get(CryptoCurrencyEntity_.date), dateFrom.atStartOfDay()));

            Optional<Predicate> dateToPredicate = Optional.ofNullable(dto.getDateTo())
                    .map(dateTo -> cb.lessThanOrEqualTo(root.get(CryptoCurrencyEntity_.date), dateTo.atStartOfDay()));

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

    public Specification<CryptoCurrencyEntity> buildSpecification(CryptoCurrencyDetailsParamsDTO dto) {
        return (root, query, cb) -> {
            Optional<Predicate> codePredicate = Optional.ofNullable(dto.getCode())
                    .map(code -> cb.like(cb.lower(root.get(CryptoCurrencyEntity_.code)), code.toLowerCase()));

            Optional<Predicate> namePredicate = Optional.ofNullable(dto.getName())
                    .map(name -> cb.like(cb.lower(root.get(CryptoCurrencyEntity_.name)), name.toLowerCase()));

            List<Predicate> predicates = Stream.of(
                            codePredicate,
                            namePredicate
                    )
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
