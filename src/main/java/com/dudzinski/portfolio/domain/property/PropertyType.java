package com.dudzinski.portfolio.domain.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public enum PropertyType {

    APARTMENT("Mieszkanie"),
    HOUSE("Dom"),
    LAND("Działka"),
    SERVICE("Lokal Usługowy"),
    OTHERS("Inne");

    private final String description;

    public static PropertyType from(String type) {
        return PropertyType.valueOf(type.toUpperCase());
    }
}