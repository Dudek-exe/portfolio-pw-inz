package com.dudzinski.portfolio.infrastructure.external.nbp.impl;


import com.dudzinski.portfolio.domain.currency.CurrencyService;
import com.dudzinski.portfolio.infrastructure.external.nbp.NbpApi;
import com.dudzinski.portfolio.infrastructure.external.nbp.NbpApiConstants;
import com.dudzinski.portfolio.infrastructure.external.nbp.NbpCurrency;
import com.dudzinski.portfolio.infrastructure.external.nbp.NbpCurrencyTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class NbpApiImpl implements NbpApi {

    private final RestTemplate restTemplate;
    private final CurrencyService currencyService;

    @Autowired
    public NbpApiImpl(RestTemplate restTemplate, CurrencyService currencyService) {
        this.restTemplate = restTemplate;
        this.currencyService = currencyService;
    }

    @Override
    public NbpCurrencyTable getNewestTable() {

        NbpCurrencyTable[] tableFromApi = restTemplate.getForObject(NbpApiConstants.NBP_URL, NbpCurrencyTable[].class);


        if (Objects.nonNull(tableFromApi)) {
            List<NbpCurrencyTable> nbpCurrencyTablesAsList = Arrays.stream(tableFromApi).toList();

            nbpCurrencyTablesAsList.stream()
                    .map(this::customizeNbpCurrency)
                    .forEach(
                            currencyRatesList -> currencyRatesList.forEach(
                                    currencyObject -> currencyService.updateCurrency(
                                            currencyObject.getCurrency(),
                                            currencyObject.getMid(),
                                            currencyObject.getCode(),
                                            currencyObject.getDate()
                                    )
                            )
                    );
        }
        return null;
    }

    private List<NbpCurrency> customizeNbpCurrency(NbpCurrencyTable table) {
        List<NbpCurrency> rates = table.getRates();
        rates.forEach(rate -> rate.setDate(LocalDate.parse(table.getEffectiveDate())));
        return rates;
    }
}
