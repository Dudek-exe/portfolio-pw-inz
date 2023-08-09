package com.dudzinski.portfolio.infrastructure.external.coinpaprica.impl;

import com.dudzinski.portfolio.application.cryptocurrency.mapper.CryptoCurrencyMapper;
import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyService;
import com.dudzinski.portfolio.infrastructure.external.coinpaprica.CoinPapricaApi;
import com.dudzinski.portfolio.infrastructure.external.coinpaprica.CoinPapricaApiConstants;
import com.dudzinski.portfolio.infrastructure.external.coinpaprica.CoinPapricaCryptoCurrency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoinPapricaApiImpl implements CoinPapricaApi {

    private final RestTemplate restTemplate;
    private final CryptoCurrencyService cryptoCurrencyService;
    private final CryptoCurrencyMapper cryptoCurrencyMapper;

    @Override
    public void getNewestList() {
        log.info("Pobieranie z Coin Paprica Api rozpoczęło się: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
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