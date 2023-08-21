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
public class CryptoCurrencyDetailsResultDTO {

    private Long id;

    private String name;

    private BigDecimal rate;

    private String code;

    private String date;

    private Double pc15m;

    private Double pc30m;

    private Double pc1h;

    private Double pc6h;

    private Double pc12h;

    private Double pc1d;
}
