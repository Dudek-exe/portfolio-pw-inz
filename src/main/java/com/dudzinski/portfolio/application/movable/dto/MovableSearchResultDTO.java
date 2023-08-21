package com.dudzinski.portfolio.application.movable.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovableSearchResultDTO {

    private Long id;

    private String name;

    private String brand;

    private String model;

    private String movableType;

    private int productionYear;

    private BigDecimal purchasePrice;

    private BigDecimal estimatedValue;
}
