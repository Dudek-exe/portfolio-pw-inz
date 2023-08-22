package com.dudzinski.portfolio.application.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioSearchParamsDTO {

    private String name;

    private Double priceFrom;

    private Double priceTo;

    private Pageable pageable;
}