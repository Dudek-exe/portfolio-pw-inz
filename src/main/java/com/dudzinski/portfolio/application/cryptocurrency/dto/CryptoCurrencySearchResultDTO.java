package com.dudzinski.portfolio.application.cryptocurrency.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CryptoCurrencySearchResultDTO {

    private Long id;

    private String name;

    private BigDecimal rate;

    private String code;

    private String date;

}
