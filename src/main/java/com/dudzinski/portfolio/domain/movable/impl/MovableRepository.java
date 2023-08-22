package com.dudzinski.portfolio.domain.movable.impl;

import com.dudzinski.portfolio.application.movable.dto.MovableSearchParamsDTO;
import com.dudzinski.portfolio.domain.movable.MovableEntity;
import com.dudzinski.portfolio.domain.movable.MovableEntity_;
import com.dudzinski.portfolio.domain.util.SecurityUtils;
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
class MovableRepository {

    private final MovableJpaRepository movableJpaRepository;

    public MovableEntity save(MovableEntity movable) {
        return movableJpaRepository.save(movable);
    }

    Page<MovableEntity> findAll(Specification<MovableEntity> spec, Pageable pageable) {
        return movableJpaRepository.findAll(spec, pageable);
    }

    public MovableEntity getById(Long movableId) {
        return movableJpaRepository.findById(movableId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Specification<MovableEntity> buildSpecification(MovableSearchParamsDTO dto) {
        return (root, query, cb) -> {
            Optional<Predicate> namePredicate = Optional.ofNullable(dto.getName())
                    .map(name -> cb.like(cb.lower(root.get(MovableEntity_.name)), name.toLowerCase()));

            Optional<Predicate> brandPredicate = Optional.ofNullable(dto.getBrand())
                    .map(brand -> cb.like(cb.lower(root.get(MovableEntity_.brand)), brand.toLowerCase()));

            Optional<Predicate> securityPredicate = Optional.of(
                    cb.equal(root.get(MovableEntity_.usernameOwner), SecurityUtils.getLoggedUserLogin()));

            List<Predicate> predicates = Stream.of(
                            namePredicate,
                            brandPredicate,
                            securityPredicate
                    )
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public void delete(Long movableId) {
        movableJpaRepository.deleteById(movableId);
    }
}
