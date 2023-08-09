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

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    List<NewAssetResponse> getAll(@RequestParam(required = false) String name, @AuthenticationPrincipal String externalId) {
        System.out.println("External id usera w get All assets " + externalId);
        if (name == null) {
            return assetService.getAll();
        } else {
            return assetService.findAllByNameContainsIgnoreCase(name);
        }

    }

    @PostMapping
    AssetEntity save(@RequestBody NewAssetRequest newAssetRequest) {
        return assetService.createNewAsset(newAssetRequest.getName(), newAssetRequest.getPrice());
    }

}
