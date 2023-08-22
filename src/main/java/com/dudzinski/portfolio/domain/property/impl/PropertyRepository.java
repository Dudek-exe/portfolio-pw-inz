package com.dudzinski.portfolio.domain.property.impl;

import com.dudzinski.portfolio.application.property.dto.PropertySearchParamsDTO;
import com.dudzinski.portfolio.domain.property.PropertyEntity;
import com.dudzinski.portfolio.domain.property.PropertyEntity_;
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
class PropertyRepository {

    private final PropertyJpaRepository propertyJpaRepository;

    public PropertyEntity save(PropertyEntity property) {
        return propertyJpaRepository.save(property);
    }

    Page<PropertyEntity> findAll(Specification<PropertyEntity> spec, Pageable pageable) {
        return propertyJpaRepository.findAll(spec, pageable);
    }

    public PropertyEntity getById(Long propertyId) {
        return propertyJpaRepository.findById(propertyId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Specification<PropertyEntity> buildSpecification(PropertySearchParamsDTO dto) {
        return (root, query, cb) -> {
            Optional<Predicate> namePredicate = Optional.ofNullable(dto.getName())
                    .map(name -> cb.like(cb.lower(root.get(PropertyEntity_.name)), name.toLowerCase()));

            Optional<Predicate> securityPredicate = Optional.of(
                    cb.equal(root.get(PropertyEntity_.usernameOwner), SecurityUtils.getLoggedUserLogin()));

            List<Predicate> predicates = Stream.of(
                            namePredicate,
                            securityPredicate
                    )
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public void delete(Long propertyId) {
        propertyJpaRepository.deleteById(propertyId);
    }
}
