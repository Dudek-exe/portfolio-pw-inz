package com.dudzinski.portfolio.domain.movable.impl;

import com.dudzinski.portfolio.domain.movable.MovableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
interface MovableJpaRepository extends JpaRepository<MovableEntity, Long>, JpaSpecificationExecutor<MovableEntity> {
}
