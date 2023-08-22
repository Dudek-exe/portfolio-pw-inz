package com.dudzinski.portfolio.application.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioSearchResultDTO {

    private Long id;

    private String name;

    private String type;

    private Double quantity;

    private BigDecimal price;
}
