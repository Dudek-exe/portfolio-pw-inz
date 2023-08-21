package com.dudzinski.portfolio.application.movable.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovablePersistDTO {

    private String name;

    private String brand;

    private String model;

    private BigDecimal purchasePrice;

    private BigDecimal estimatedValue;

    private int productionYear;

    private String movableType;

}
