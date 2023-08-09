package com.dudzinski.portfolio.application.currency.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class CurrencyResponseDTO {

    private final Long id;

    private final String name;

    private final BigDecimal rate;

    private final String code;

}
