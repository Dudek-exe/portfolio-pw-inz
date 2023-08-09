package com.dudzinski.portfolio.job.nbp.impl;

import com.dudzinski.portfolio.infrastructure.external.nbp.NbpApi;
import com.dudzinski.portfolio.job.nbp.NbpCurrencyTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
class NbpCurrencyTaskImpl implements NbpCurrencyTask {

    private final NbpApi nbpApi;

    @Value("${portfolio-app.jobs.nbp.enabled}")
    private boolean isEnabled;

    @Autowired
    public NbpCurrencyTaskImpl(NbpApi nbpApiImpl) {
        this.nbpApi = nbpApiImpl;
    }

    @Scheduled(fixedDelay = 86400000)
    @Override
    public void updateNbpRates() {
        if (isEnabled) {
            log.info("Pobieranie z Api NBP rozpoczęło się: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            nbpApi.getNewestTable();
        }
    }

}
