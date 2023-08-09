package com.dudzinski.portfolio.job.metalapi.impl;

import com.dudzinski.portfolio.infrastructure.external.metalapi.MetalApi;
import com.dudzinski.portfolio.job.metalapi.MetalApiTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
class MetalApiTaskImpl implements MetalApiTask {

    private final MetalApi metalApi;

    @Value("${portfolio-app.jobs.metal-api.enabled}")
    private boolean isEnabled;

    @Autowired
    public MetalApiTaskImpl(MetalApi metalApi) {
        this.metalApi = metalApi;
    }

    @Scheduled(fixedDelay = 120000)
    @Override
    public void updateMetalApiRates() {
        if (isEnabled) {
            log.info("Pobieranie z Api MetalApi rozpoczęło się: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            metalApi.getNewestRates();
        }
    }
}
