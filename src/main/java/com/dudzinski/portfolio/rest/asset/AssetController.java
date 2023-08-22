package com.dudzinski.portfolio.rest.asset;

import com.dudzinski.portfolio.domain.asset.AssetEntity;
import com.dudzinski.portfolio.domain.asset.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "asset")
public class AssetController {

    final AssetService assetService;

    @Autowired
    public AssetController(AssetService assetServiceImpl) {
        this.assetService = assetServiceImpl;
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @GetMapping
    List<NewAssetResponse> getAll(@RequestParam(required = false) String name, @AuthenticationPrincipal String externalId) {
        if (name == null) {
            return assetService.getAll();
        } else {
            return assetService.findAllByNameContainsIgnoreCase(name);
        }

    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @PostMapping
    AssetEntity save(@RequestBody NewAssetRequest newAssetRequest) {
        return assetService.createNewAsset(newAssetRequest.getName(), newAssetRequest.getPrice());
    }

}
