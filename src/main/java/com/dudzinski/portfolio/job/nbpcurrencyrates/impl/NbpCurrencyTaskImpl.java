package com.dudzinski.portfolio.job.nbpcurrencyrates.impl;

import com.dudzinski.portfolio.infrastructure.external.nbp.NbpApi;
import com.dudzinski.portfolio.job.nbpcurrencyrates.NbpCurrencyTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
class NbpCurrencyTaskImpl implements NbpCurrencyTask {

    private final NbpApi nbpApi;

    @Autowired
    public NbpCurrencyTaskImpl(NbpApi nbpApiImpl) {
        this.nbpApi = nbpApiImpl;
    }

    @Scheduled(fixedDelay = 86400000)
    @Override
    public void updateNbpRates() {
        log.info("Pobieranie z Api NBP rozpoczęło się: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        nbpApi.getNewestTable();
    }

}
