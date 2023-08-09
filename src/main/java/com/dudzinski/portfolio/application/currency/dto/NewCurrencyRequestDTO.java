package com.dudzinski.portfolio.application.currency.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewCurrencyRequestDTO {

    private String name;

    private String rate;

    private String code;
}
