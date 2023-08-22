package com.dudzinski.portfolio.application.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioPersistDTO {

    private String name;

    private String type;

    private Double quantity;

    private BigDecimal price;
}
