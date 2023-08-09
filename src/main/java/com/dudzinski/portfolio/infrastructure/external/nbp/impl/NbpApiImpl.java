package com.dudzinski.portfolio.infrastructure.external.nbp.impl;


import com.dudzinski.portfolio.domain.currency.CurrencyService;
import com.dudzinski.portfolio.infrastructure.external.nbp.NbpApi;
import com.dudzinski.portfolio.infrastructure.external.nbp.NbpApiConstants;
import com.dudzinski.portfolio.infrastructure.external.nbp.NbpCurrencyTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

        NbpCurrencyTable[] tableFromApi = restTemplate
                .getForObject(NbpApiConstants.NBP_URL, NbpCurrencyTable[].class);

        if (Objects.nonNull(tableFromApi)) {
            tableFromApi[0].getRates()
                    .forEach(currencyObject ->
                            currencyService.updateCurrency(
                                    currencyObject.getCurrency(),
                                    currencyObject.getMid(),
                                    currencyObject.getCode())
                    );
        }
        return null;
    }

}
