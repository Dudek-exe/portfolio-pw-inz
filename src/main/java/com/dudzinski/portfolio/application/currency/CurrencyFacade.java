package com.dudzinski.portfolio.application.currency;

import com.dudzinski.portfolio.application.currency.dto.CurrencyResponseDTO;
import com.dudzinski.portfolio.application.currency.dto.CurrencySearchParamsDTO;
import com.dudzinski.portfolio.domain.currency.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrencyFacade {

    private final CurrencyService currencyService;

    public Page<CurrencyResponseDTO> search(CurrencySearchParamsDTO currencySearchParamsDTO) {
        return currencyService.findAll(currencySearchParamsDTO);
    }
}
