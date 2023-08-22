package com.dudzinski.portfolio.domain.portfolio.impl;

import com.dudzinski.portfolio.application.portfolio.dto.PortfolioSearchParamsDTO;
import com.dudzinski.portfolio.domain.portfolio.PortfolioEntity;
import com.dudzinski.portfolio.domain.portfolio.PortfolioEntity_;
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
class PortfolioRepository {

    private final PortfolioJpaRepository portfolioJpaRepository;

    public PortfolioEntity save(PortfolioEntity portfolio) {
        return portfolioJpaRepository.save(portfolio);
    }

    Page<PortfolioEntity> findAll(Specification<PortfolioEntity> spec, Pageable pageable) {
        return portfolioJpaRepository.findAll(spec, pageable);
    }

    public PortfolioEntity getById(Long portfolioId) {
        return portfolioJpaRepository.findById(portfolioId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Specification<PortfolioEntity> buildSpecification(PortfolioSearchParamsDTO dto) {
        return (root, query, cb) -> {
            Optional<Predicate> namePredicate = Optional.ofNullable(dto.getName())
                    .map(name -> cb.like(cb.lower(root.get(PortfolioEntity_.name)), name.toLowerCase()));

            List<Predicate> predicates = Stream.of(
                            namePredicate
                    )
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public void delete(Long portfolioId) {
        portfolioJpaRepository.deleteById(portfolioId);
    }
}
