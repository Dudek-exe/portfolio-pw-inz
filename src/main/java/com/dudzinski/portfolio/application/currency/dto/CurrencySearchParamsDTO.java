package com.dudzinski.portfolio.application.currency.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencySearchParamsDTO {

    private String name;

    private String code;

    private int start;

    private int end;

    private Pageable pageable;
}
