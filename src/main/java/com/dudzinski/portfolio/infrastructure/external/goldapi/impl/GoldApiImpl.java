package com.dudzinski.portfolio.infrastructure.external.goldapi.impl;

import com.dudzinski.portfolio.domain.metal.MetalService;
import com.dudzinski.portfolio.infrastructure.external.goldapi.GoldApi;
import com.dudzinski.portfolio.infrastructure.external.goldapi.GoldApiConstants;
import com.dudzinski.portfolio.infrastructure.external.goldapi.GoldApiMetal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
class GoldApiImpl implements GoldApi {

    private static final String GOLD_CODE = "XAG";
    private static final String SILVER_CODE = "XAU";
    private final RestTemplate restTemplate;
    private final MetalService metalService;

    @Autowired
    public GoldApiImpl(RestTemplate restTemplate, MetalService metalService) {
        this.restTemplate = restTemplate;
        this.metalService = metalService;
    }

    @Override
    public void getNewestRates() {
        GoldApiMetal goldApiMetal = restTemplate.getForObject(GoldApiConstants.GOLDAPI_GOLD_URL, GoldApiMetal.class);

        if (Objects.nonNull(goldApiMetal)) {
            List<String> wantedKeys = List.of(GOLD_CODE, SILVER_CODE);

            goldApiMetal.getRates()
                    .entrySet().stream()
                    .filter(e -> wantedKeys.contains(e.getKey()))
                    .forEach(e -> metalService.updateMetal(e.getKey(), e.getValue()
                            )
                    );
        }
    }
}
