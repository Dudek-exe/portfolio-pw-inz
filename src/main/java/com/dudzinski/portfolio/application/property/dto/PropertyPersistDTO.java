package com.dudzinski.portfolio.application.property.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyPersistDTO {

    private String name;

    private String address;

    private Double area;

    private BigDecimal purchasePrice;

    private LocalDate purchaseDate;

    private BigDecimal estimatedValue;

    private String propertyType;
}
