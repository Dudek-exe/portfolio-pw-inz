package com.dudzinski.portfolio.rest.asset;

import com.dudzinski.portfolio.domain.asset.AssetEntity;

public class NewAssetResponse {

    private Long id;
    private String name;
    private String price;

    public NewAssetResponse(Long id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public NewAssetResponse() {
    }

    public static NewAssetResponse from(AssetEntity assetEntity) {
        return new NewAssetResponse(assetEntity.getId(), assetEntity.getName(), assetEntity.getPrice());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
