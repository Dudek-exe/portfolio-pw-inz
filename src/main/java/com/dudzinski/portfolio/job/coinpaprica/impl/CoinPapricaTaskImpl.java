package com.dudzinski.portfolio.job.coinpaprica.impl;

import com.dudzinski.portfolio.infrastructure.external.coinpaprica.CoinPapricaApi;
import com.dudzinski.portfolio.job.coinpaprica.CoinPapricaTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
class CoinPapricaTaskImpl implements CoinPapricaTask {

    private final CoinPapricaApi coinPapricaApi;

    @Value("${portfolio-app.jobs.coin-paprica.enabled}")
    private boolean isEnabled;

    @Scheduled(fixedDelay = 120000)
    @Override
    public void updateCoinPapricaRates() {
        if (isEnabled) {
            log.info("Pobieranie z Coin Paprica Api rozpoczęło się: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            coinPapricaApi.getNewestList();
        }
    }
}
