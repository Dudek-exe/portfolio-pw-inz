package com.dudzinski.portfolio.application.car.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarSearchResultDTO {

    private Long id;

    private String name;

    private String brand;

    private String model;

    private int productionYear;

    private BigDecimal purchasePrice;

    private BigDecimal estimatedValue;
}
