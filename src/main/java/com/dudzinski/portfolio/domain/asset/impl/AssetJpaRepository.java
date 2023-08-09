package com.dudzinski.portfolio.domain.asset.impl;

import com.dudzinski.portfolio.domain.asset.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface AssetJpaRepository extends JpaRepository<AssetEntity, Long> {

    List<AssetEntity> findAllByNameContainsIgnoreCase(String name);

}
