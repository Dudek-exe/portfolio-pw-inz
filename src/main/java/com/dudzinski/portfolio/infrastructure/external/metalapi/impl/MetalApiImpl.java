package com.dudzinski.portfolio.infrastructure.external.metalapi.impl;

import com.dudzinski.portfolio.domain.metal.MetalService;
import com.dudzinski.portfolio.infrastructure.external.metalapi.MetalApi;
import com.dudzinski.portfolio.infrastructure.external.metalapi.MetalApiConstants;
import com.dudzinski.portfolio.infrastructure.external.metalapi.MetalApiMetal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
class MetalApiImpl implements MetalApi {

    private final RestTemplate restTemplate;
    private final MetalService metalService;

    @Override
    public void getNewestRates() {
        MetalApiMetal metalApiMetal = restTemplate.getForObject(MetalApiConstants.METAL_API, MetalApiMetal.class);

        if (Objects.nonNull(metalApiMetal)) {
            metalApiMetal.getRates().forEach(metalService::updateMetal);
        }
    }
}
