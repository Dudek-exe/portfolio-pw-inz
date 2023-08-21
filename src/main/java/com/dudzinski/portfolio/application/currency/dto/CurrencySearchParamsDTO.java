package com.dudzinski.portfolio.application.currency.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencySearchParamsDTO {

    private String name;

    private String code;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private Pageable pageable;
}
