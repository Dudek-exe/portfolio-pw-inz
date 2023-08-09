package com.dudzinski.portfolio.domain.asset;

import com.dudzinski.portfolio.rest.asset.NewAssetResponse;

import java.util.List;

public interface AssetService {

    AssetEntity createNewAsset(String name, String price);

    List<NewAssetResponse> getAll();

    List<NewAssetResponse> findAllByNameContainsIgnoreCase(String name);

}
