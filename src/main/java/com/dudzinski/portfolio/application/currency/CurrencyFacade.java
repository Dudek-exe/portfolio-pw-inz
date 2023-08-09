package com.dudzinski.portfolio.application.currency;

import com.dudzinski.portfolio.application.currency.dto.NewCurrencyRequestDTO;
import com.dudzinski.portfolio.application.currency.dto.NewCurrencyResponseDTO;
import com.dudzinski.portfolio.domain.currency.CurrencyEntity;
import com.dudzinski.portfolio.domain.currency.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrencyFacade {

    private final CurrencyService currencyService;

    public CurrencyEntity createNewCurrency(NewCurrencyRequestDTO dto) {
        return currencyService.createNewCurrency(dto.getName(), dto.getRate(), dto.getCode());
    }

    public Page<NewCurrencyResponseDTO> findAll(String name, String code, int start, int end) {
        return currencyService.findAll(name, code, start, end);
    }

}
