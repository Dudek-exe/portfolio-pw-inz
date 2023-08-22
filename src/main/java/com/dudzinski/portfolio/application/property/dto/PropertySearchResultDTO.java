package com.dudzinski.portfolio.application.property.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertySearchResultDTO {

    private Long id;

    private String name;

    private String address;

    private Double area;

    private String propertyType;

    private BigDecimal purchasePrice;

    private String purchaseDate;

    private BigDecimal estimatedValue;
}
