package com.dudzinski.portfolio.domain.portfolio;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public enum PortfolioType {

    APARTMENT("Mieszkanie"),
    HOUSE("Dom"),
    LAND("Działka"),
    SERVICE("Lokal Usługowy"),
    OTHERS("Inne");

    private final String description;

    public static PortfolioType from(String type) {
        return PortfolioType.valueOf(type.toUpperCase());
    }
}