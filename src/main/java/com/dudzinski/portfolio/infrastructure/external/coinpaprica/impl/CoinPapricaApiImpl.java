package com.dudzinski.portfolio.infrastructure.external.coinpaprica.impl;

import com.dudzinski.portfolio.application.cryptocurrency.mapper.CryptoCurrencyMapper;
import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyService;
import com.dudzinski.portfolio.infrastructure.external.coinpaprica.CoinPapricaApi;
import com.dudzinski.portfolio.infrastructure.external.coinpaprica.CoinPapricaApiConstants;
import com.dudzinski.portfolio.infrastructure.external.coinpaprica.CoinPapricaCryptoCurrency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CoinPapricaApiImpl implements CoinPapricaApi {

    private final RestTemplate restTemplate;
    private final CryptoCurrencyService cryptoCurrencyService;
    private final CryptoCurrencyMapper cryptoCurrencyMapper;

    @Override
    public void getNewestList() {
        CoinPapricaCryptoCurrency[] listFromApi = restTemplate.getForObject(
                CoinPapricaApiConstants.COIN_PAPRICA_URL,
                CoinPapricaCryptoCurrency[].class
        );

        if (Objects.nonNull(listFromApi)) {
            Arrays.stream(listFromApi)
                    .map(cryptoCurrencyMapper::toEntity)
                    .limit(100)
                    .forEach(cryptoCurrencyService::saveFromExternalApi);
        }
    }

}