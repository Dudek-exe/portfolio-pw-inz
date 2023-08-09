package com.dudzinski.portfolio.domain.asset.impl;

import com.dudzinski.portfolio.domain.asset.AssetEntity;
import com.dudzinski.portfolio.domain.asset.AssetService;
import com.dudzinski.portfolio.rest.asset.NewAssetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    @Override
    public AssetEntity createNewAsset(String name, String price) {
        AssetEntity newAssetEntity = new AssetEntity(name, price);
        return assetRepository.save(newAssetEntity);
    }

    @Override
    public List<NewAssetResponse> getAll() {
        return assetRepository.findAll().stream()
                .map(NewAssetResponse::from)
                .collect(toList());
    }

    @Override
    public List<NewAssetResponse> findAllByNameContainsIgnoreCase(String name) {
        return assetRepository.findAllByNameContainsIgnoreCase(name).stream()
                .map(NewAssetResponse::from)
                .collect(toList());
    }
}
