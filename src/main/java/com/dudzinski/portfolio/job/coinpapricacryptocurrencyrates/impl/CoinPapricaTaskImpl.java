package com.dudzinski.portfolio.job.coinpapricacryptocurrencyrates.impl;

import com.dudzinski.portfolio.infrastructure.external.coinpaprica.CoinPapricaApi;
import com.dudzinski.portfolio.job.coinpapricacryptocurrencyrates.CoinPapricaTask;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CoinPapricaTaskImpl implements CoinPapricaTask {

    private final CoinPapricaApi coinPapricaApi;

    @Scheduled(fixedDelay = 120000)
    @Override
    public void updateCoinPapricaRates() {
        coinPapricaApi.getNewestList();
    }
}
