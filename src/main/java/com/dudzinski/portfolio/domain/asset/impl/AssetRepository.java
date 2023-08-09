package com.dudzinski.portfolio.domain.asset.impl;

import com.dudzinski.portfolio.domain.asset.AssetEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AssetRepository {

    private final AssetJpaRepository assetJpaRepository;

    public List<AssetEntity> findAllByNameContainsIgnoreCase(String name) {
        return assetJpaRepository.findAllByNameContainsIgnoreCase(name);
    }

    public AssetEntity save(AssetEntity asset) {
        return assetJpaRepository.save(asset);
    }

    public List<AssetEntity> findAll() {
        return assetJpaRepository.findAll();
    }

}
