package com.dudzinski.portfolio.application.currency.dto;

import com.dudzinski.portfolio.domain.currency.CurrencyEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class NewCurrencyResponseDTO {

    private final Long id;

    private final String name;

    private final BigDecimal rate;

    private final String code;

    public static NewCurrencyResponseDTO from(CurrencyEntity currencyEntity) {
        return new NewCurrencyResponseDTO(currencyEntity.getId(), currencyEntity.getName(), currencyEntity.getRate(), currencyEntity.getCode());
    }

}
